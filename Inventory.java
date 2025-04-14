import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

class Inventory {
    private List<Part> parts;
    private String storageLocation;
    private File inventoryFile;

    public Inventory(String storageLocation, String filePath) {
        this.storageLocation = storageLocation;
        this.parts = new ArrayList<>();
        this.inventoryFile = new File(filePath);
        readData(inventoryFile); // Read existing parts from file
    }

    public Inventory(String filePath) {

    }

    public Inventory() {
    }

    public List<Part> getParts() { return parts; }
    public String getStorageLocation() { return storageLocation; }

    public void addPart(Part part) {
        parts.add(part);
        writeToFile(); // Append the new part to file
    }
    
    public void removePart(Part part) {
        parts.remove(part);
        writeToFile(); // Rewrite parts to file after removal
    }

    public Part findPart(String identifier) {
        readData(inventoryFile); // Refresh data from file
        for (Part part : parts) {
            if (part.getPartId().equalsIgnoreCase(identifier) || 
                part.getName().equalsIgnoreCase(identifier)) {
                return part;
            }
        }
        return null;
    }


    public boolean removeParts(String partId, int quantity) {
        Part part = findPart(partId);
        if (part == null) {
            System.out.println("Part not found: " + partId);
            return false;
        }
        
        if (part.getQuantityInStock() < quantity) {
            System.out.println("Insufficient quantity for part: " + part.getName() + 
                             " (Available: " + part.getQuantityInStock() + 
                             ", Requested: " + quantity + ")");
            return false;
        }
        
        part.setQuantityInStock(part.getQuantityInStock() - quantity);
        updateInventoryFile();
        return true;
    }

    public void readData(File filename) {
        try (Scanner partFile = new Scanner(filename)) {
            while (partFile.hasNext()) {
                String[] data = partFile.nextLine().split(":");
                Part p = new Part(data[0], data[1], Double.parseDouble(data[2]), 
                                 Integer.parseInt(data[3]), data[4]);
                parts.add(p);
            }
        } catch (IOException ioe) {
            System.out.println("Error reading from file.");
        }
    }

    public void writeToFile() {
        try {
            if (!inventoryFile.exists()) {
                File parent = inventoryFile.getParentFile();
                if (parent != null) {
                    parent.mkdirs();
                }
                inventoryFile.createNewFile();
            }

            try (PrintWriter pw = new PrintWriter(new FileWriter(inventoryFile, true))) {
                for (Part part : parts) {
                    pw.println(part.toString());
                }
                System.out.println("Data appended to file: " + inventoryFile.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public List<Part> viewAllParts() {
        List<Part> partList = new ArrayList<>(); // To store all parts
        
        try (Scanner partFile = new Scanner(inventoryFile)) {
            if (!partFile.hasNext()) {
                System.out.println("No parts available in inventory.");
            } else {
                System.out.println("Parts in Inventory:");
                while (partFile.hasNext()) {
                    String data = partFile.nextLine();
                    Part part = Part.fromString(data); // Convert the line to a Part object
                    partList.add(part); // Store the part in the list
                    System.out.println(part.toString()); // Optionally print the part details
                }
            }
        } catch (IOException ioe) {
            System.out.println("Error reading parts from file.");
            ioe.printStackTrace();
        }
        
        return partList; // Return the list of parts
    }

    


    public void updateInventoryFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(inventoryFile, false))) { // false = overwrite
            for (Part part : parts) {
                pw.println(part.toString());
            }
            System.out.println("Inventory file updated.");
        } catch (IOException e) {
            System.out.println("An error occurred while updating the inventory file.");
            e.printStackTrace();
        }
    }
}
