import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class Service {
    private String serviceId;
    private String serviceType;
    private List<String> partIdsUsed;  // Store part IDs instead of Part objects
    private List<Integer> quantitiesUsed;
    private String servicePost;
    private Date serviceDate;
    private String mechanicId;

    private static final File SERVICE_FILE = new File("service.txt");
    private static List<Service> services = new ArrayList<>();
    private static Inventory inventory;  // Reference to inventory system

    public static void setInventory(Inventory inv) {
        inventory = inv;
    }

    public Service(String serviceId, String serviceType, String servicePost, Date serviceDate, String mechanicId) {
        this.serviceId = serviceId;
        this.serviceType = serviceType;
        this.servicePost = servicePost;
        this.serviceDate = serviceDate;
        this.mechanicId = mechanicId;
        this.partIdsUsed = new ArrayList<>();
        this.quantitiesUsed = new ArrayList<>();
    }

    public Service() {
        this.partIdsUsed = new ArrayList<>();
        this.quantitiesUsed = new ArrayList<>();
    }

    public String getServiceId() { return serviceId; }
    public String getServiceType() { return serviceType; }
    public List<String> getPartIdsUsed() { return partIdsUsed; }
    public List<Integer> getQuantitiesUsed() { return quantitiesUsed; }
    public String getServicePost() { return servicePost; }
    public Date getServiceDate() { return serviceDate; }
    public String getMechanicId() { return mechanicId; }

    public void setServiceId(String serviceId) { this.serviceId = serviceId; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }
    public void setServicePost(String servicePost) { this.servicePost = servicePost; }
    public void setServiceDate(Date serviceDate) { this.serviceDate = serviceDate; }
    public void setMechanicId(String mechanicId) { this.mechanicId = mechanicId; }

    public void addPartUsed(String partId, int quantity) {
        if (partId != null && !partId.isEmpty() && quantity > 0) {
            partIdsUsed.add(partId);
            quantitiesUsed.add(quantity);
        }
    }

    public double calculateTotalCost(double laborRate) {
        if (inventory == null) return laborRate;
        
        double partsCost = 0;
        for (int i = 0; i < partIdsUsed.size(); i++) {
            Part part = inventory.findPart(partIdsUsed.get(i));
            if (part != null) {
                partsCost += part.getPrice() * quantitiesUsed.get(i);
            }
        }
        return partsCost + (3*laborRate); // Assuming 3 hours of labor
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(serviceId).append(":")
          .append(serviceType).append(":")
          .append(servicePost).append(":")
          .append(serviceDate.getTime()).append(":")
          .append(mechanicId).append(":");
        
        for (int i = 0; i < partIdsUsed.size(); i++) {
            if (i > 0) sb.append(",");
            sb.append(partIdsUsed.get(i)).append("-").append(quantitiesUsed.get(i));
        }
        
        return sb.toString();
    }

    public static void readData() {
        services.clear();
        if (!SERVICE_FILE.exists()) {
            try {
                SERVICE_FILE.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating service file: " + e.getMessage());
            }
            return;
        }
    
        try (Scanner scanner = new Scanner(SERVICE_FILE)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    try {
                        String[] data = line.split(":");
                        if (data.length >= 6) {
                            String serviceId = data[0];
                            String serviceType = data[1];
                            String servicePost = data[3]; // Description is at index 3
                            Date serviceDate = new Date(Long.parseLong(data[4]));
                            String mechanicId = data[5];
                            
                            Service service = new Service(serviceId, serviceType, servicePost, serviceDate, mechanicId);
                            
                            if (!data[2].isEmpty()) {
                                String[] partsData = data[2].split(",");
                                for (String partData : partsData) {
                                    String[] partInfo = partData.split("-");
                                    if (partInfo.length == 2) {
                                        try {
                                            String partId = partInfo[0];
                                            int quantity = Integer.parseInt(partInfo[1]);
                                            service.addPartUsed(partId, quantity);
                                        } catch (NumberFormatException e) {
                                            System.out.println("Invalid part quantity format in service " + serviceId + ": " + partData);
                                        }
                                    }
                                }
                            }
                            
                            services.add(service);
                        } else {
                            System.out.println("Skipping invalid service record (insufficient fields): " + line);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error reading service file: " + e.getMessage() + " for line: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading service file: " + e.getMessage());
        }
    }
   
    public static void writeToFile() {
        try {
            if (!SERVICE_FILE.exists()) {
                SERVICE_FILE.getParentFile().mkdirs();
                SERVICE_FILE.createNewFile();
            }

            try (PrintWriter pw = new PrintWriter(new FileWriter(SERVICE_FILE, false))) {
                for (Service service : services) {
                    pw.println(service.toString());
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing to service file: " + e.getMessage());
        }
    }

    public static void addService(Service service) {
        readData();
        services.add(service);
        writeToFile();
    }

    public static Service findServiceById(String serviceId) {
        readData();
        for (Service service : services) {
            if (service.getServiceId().equalsIgnoreCase(serviceId)) {
                return service;
            }
        }
        return null;
    }

    public static List<Service> getAllServices() {
        readData();
        return new ArrayList<>(services);
    }

    public static boolean updateService(String serviceId, String serviceType, String servicePost, 
                                     Date serviceDate, String mechanicId) {
        readData();
        for (Service service : services) {
            if (service.getServiceId().equalsIgnoreCase(serviceId)) {
                service.setServiceType(serviceType);
                service.setServicePost(servicePost);
                service.setServiceDate(serviceDate);
                service.setMechanicId(mechanicId);
                writeToFile();
                return true;
            }
        }
        return false;
    }

    public static boolean deleteService(String serviceId) {
        readData();
        for (int i = 0; i < services.size(); i++) {
            if (services.get(i).getServiceId().equalsIgnoreCase(serviceId)) {
                services.remove(i);
                writeToFile();
                return true;
            }
        }
        return false;
    }

    public static String generateNextServiceId() {
        readData();
        int maxId = 0;
        
        for (Service service : services) {
            String idStr = service.getServiceId();
            if (idStr.startsWith("SRV")) {
                try {
                    int num = Integer.parseInt(idStr.substring(3));
                    if (num > maxId) {
                        maxId = num;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid service ID format: " + idStr);
                }
            }
        }
        
        return String.format("SRV%03d", maxId + 1);
    }

    public boolean completeService() {
        // First check if all parts are available
        List<String> availabilityMessages = checkPartAvailability();
        if (!availabilityMessages.isEmpty()) {
            System.out.println("Cannot complete service - parts issues:");
            availabilityMessages.forEach(System.out::println);
            return false;
        }

        // Deduct parts from inventory
        if (!deductUsedPartsFromInventory()) {
            System.out.println("Failed to deduct parts from inventory");
            return false;
        }

        System.out.println("Service completed successfully");
        return true;
    }

    public List<String> checkPartAvailability() {
        List<String> messages = new ArrayList<>();
        if (inventory == null) {
            messages.add("Inventory system not available");
            return messages;
        }
        
        // Refresh inventory data from file
        inventory.readData(inventory.inventoryFile);
        
        for (int i = 0; i < partIdsUsed.size(); i++) {
            Part part = inventory.findPart(partIdsUsed.get(i));
            if (part == null) {
                messages.add("Part not found: " + partIdsUsed.get(i));
            } else if (part.getQuantityInStock() < quantitiesUsed.get(i)) {
                messages.add("Insufficient quantity for " + part.getName() + 
                           " (Available: " + part.getQuantityInStock() + 
                           ", Needed: " + quantitiesUsed.get(i) + ")");
            }
        }
        
        return messages;
    }

    public boolean deductUsedPartsFromInventory() {
        if (inventory == null) return false;
        
        boolean allPartsAvailable = true;
        
        for (int i = 0; i < partIdsUsed.size(); i++) {
            Part part = inventory.findPart(partIdsUsed.get(i));
            if (part == null || part.getQuantityInStock() < quantitiesUsed.get(i)) {
                allPartsAvailable = false;
                break;
            }
        }
        
        if (!allPartsAvailable) return false;
        
        for (int i = 0; i < partIdsUsed.size(); i++) {
            Part part = inventory.findPart(partIdsUsed.get(i));
            part.setQuantityInStock(part.getQuantityInStock() - quantitiesUsed.get(i));
        }
        
        inventory.updateInventoryFile();
        return true;
    }

    public static List<Service> findServicesByMechanic(String mechanicId) {
        readData();
        List<Service> result = new ArrayList<>();
        for (Service service : services) {
            if (service.getMechanicId().equalsIgnoreCase(mechanicId)) {
                result.add(service);
            }
        }
        return result;
    }

    public static List<Service> findServicesByType(String serviceType) {
        readData();
        List<Service> result = new ArrayList<>();
        for (Service service : services) {
            if (service.getServiceType().equalsIgnoreCase(serviceType)) {
                result.add(service);
            }
        }
        return result;
    }

    public void printServiceDetails() {
        System.out.println("Service ID: " + serviceId);
        System.out.println("Service Type: " + serviceType);
        System.out.println("Mechanic ID: " + mechanicId);
        System.out.println("Date: " + serviceDate);
        System.out.println("Parts Used:");
        
        if (inventory != null) {
            for (int i = 0; i < partIdsUsed.size(); i++) {
                Part part = inventory.findPart(partIdsUsed.get(i));
                if (part != null) {
                    System.out.println(" - " + part.getName() + 
                                     " (Qty: " + quantitiesUsed.get(i) + 
                                     ", Part ID: " + part.getPartId() + ")");
                } else {
                    System.out.println(" - [Unknown Part] (ID: " + partIdsUsed.get(i) + 
                                     ", Qty: " + quantitiesUsed.get(i) + ")");
                }
            }
        } else {
            for (int i = 0; i < partIdsUsed.size(); i++) {
                System.out.println(" - Part ID: " + partIdsUsed.get(i) + 
                                 ", Qty: " + quantitiesUsed.get(i));
            }
        }
    }

    
    
}