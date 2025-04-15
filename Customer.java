import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Customer {
    private String customerId;
    private String name;
    private String phoneNumber;
    private List<Appointment> appointments;
    private List<Service> services;
    private LoyaltyCard loyaltyCard;
    private int stamps;

    static File CustFile = new File("customer.txt");
    List<Customer> customersList = new ArrayList<>();

    public Customer() {}

    public Customer(String customerId, String name, String phoneNumber) {
        this.customerId = customerId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.appointments = new ArrayList<>();
        this.services = new ArrayList<>();
        this.stamps = 0; // Default stamps to 0
    }

    public Customer(String customerId, String name, String phoneNumber, String stamps) {
        this(customerId, name, phoneNumber);
        this.stamps = Integer.parseInt(stamps);
    }

    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }
    public List<Appointment> getAppointments() { return appointments; }
    public List<Service> getServices() { return services; }
    public LoyaltyCard getLoyaltyCard() { return loyaltyCard; }
    public int getStamps() { return stamps; }

    public void setLoyaltyCard(LoyaltyCard loyaltyCard) { this.loyaltyCard = loyaltyCard; }
    public void setStamps(int stamps) { this.stamps = stamps; }

    public void addAppointment(Appointment appointment) { 
        appointments.add(appointment); 
        this.stamps++; // Increment stamps for each appointment
        checkForDiscount();
    }

    public void addService(Service service) { services.add(service); }

    private void checkForDiscount() {
        if (this.stamps >= 8) {
            if (this.loyaltyCard != null) {
                this.loyaltyCard.setDiscountAvailable(10.0); // 10% discount
            }
        }
    }

    @Override
    public String toString() {
        return customerId + ":" + name + ":" + phoneNumber + ":" + 
               appointments.size() + ":" + services.size() + ":" + stamps;
    }

    public void readData(File filename) {
        try (Scanner customerFile = new Scanner(filename)) {
            while (customerFile.hasNext()) {
                String[] data = customerFile.nextLine().split(":");
                if (data.length >= 6) { // Ensure the line has enough fields
                    Customer p = new Customer(data[0], data[1], data[2], data[5]);
                    customersList.add(p);
                }
            }
        } catch (IOException ioe) {
            System.out.println("Error reading customer data: " + ioe.getMessage());
        }
    }

    public void writeToFile(File file, List<Customer> customers) {
        try {
            if (!file.exists()) {
                File parent = file.getParentFile();
                if (parent != null) {
                    parent.mkdirs();
                }
                file.createNewFile();
            }

            try (PrintWriter pw = new PrintWriter(new FileWriter(file))) { // Overwrite file
                for (Customer customer : customers) {
                    pw.println(customer.toString());
                }
                System.out.println("Data written to file: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public void addCust(Customer p) {
        customersList.add(p);
        writeToFile(CustFile, customersList);  // Overwrite the file with the updated customer list
    }

    public static String generateNextCustomerId() {
        int maxId = 0;

        if (CustFile.exists()) {
            try (Scanner customerFile = new Scanner(CustFile)) {
                while (customerFile.hasNext()) {
                    String[] data = customerFile.nextLine().split(":");
                    try {
                        if (data[0].startsWith("CST")) {
                            int currentId = Integer.parseInt(data[0].substring(3));
                            if (currentId > maxId) {
                                maxId = currentId;
                            }
                        }
                    } catch (NumberFormatException e) {
                        continue;
                    }
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

        int nextId = maxId + 1;

        return String.format("CST%03d", nextId);
    }

    public LoyaltyCard findLoyaltyCardByCustomerId(String customerId) {
        readData(CustFile);

        for (Customer customer : customersList) {
            if (customer.getCustomerId().equalsIgnoreCase(customerId)) {
                return customer.getLoyaltyCard(); // Return the loyalty card if found
            }
        }
        return null;
    }
}
