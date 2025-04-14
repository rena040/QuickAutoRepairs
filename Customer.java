import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

class Customer {
    private String customerId;
    private String name;
    private String phoneNumber;
    private List<Appointment> appointments;
    private List<Service> services;
    private LoyaltyCard loyaltyCard;

    // File to store customer data
    static File CustFile = new File("customer.txt");
    List<Customer> customersList = new ArrayList<>();

    public Customer() {}

    public Customer(String customerId, String name, String phoneNumber) {
        this.customerId = customerId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.appointments = new ArrayList<>();
        this.services = new ArrayList<>();
        LoyaltyCard lc =  new LoyaltyCard(customerId, customerId, "defaultValue"); // Replace null with a valid value
        this.loyaltyCard = lc; // Initialize loyalty card with customerId
    }

    public Customer(String customerId, String name, String phoneNumber, String dummyapt, String dummyService, String dummyLoyaltyCard) {
        this(customerId, name, phoneNumber);
        
        // Additional logic for dummyInt and dummyString can be added here if needed
    }

    // Getters and setters
    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }
    public List<Appointment> getAppointments() { return appointments; }
    public List<Service> getServices() { return services; }
    public LoyaltyCard getLoyaltyCard() { return loyaltyCard; }

    public void setLoyaltyCard(LoyaltyCard loyaltyCard) { this.loyaltyCard = loyaltyCard; }
    public void addAppointment(Appointment appointment) { 
        appointments.add(appointment); 
        this.loyaltyCard.addStamp(); // Add a stamp for each appointment
        checkForDiscount();
    }
    public void addService(Service service) { services.add(service); }

    private void checkForDiscount() {
        if (this.loyaltyCard.getStamps() >= 8) {
            this.loyaltyCard.setDiscountAvailable(10.0); // 10% discount
            // Optionally reset stamps or implement other logic
        }
    }

    @Override
    public String toString() {
        return customerId + ":" + name + ":" + phoneNumber + ":" + appointments + ":" + services + ":" + loyaltyCard;
    }

    // Method to read existing customer data from file
    public void readData(File filename) {
        try (Scanner customerFile = new Scanner(filename)) {
            while (customerFile.hasNext()) {
                String[] data = customerFile.nextLine().split(":");
                Customer p = new Customer(data[0], data[1], data[2]);
                customersList.add(p);
            }
        } catch (IOException ioe) {
            // Handle error if file reading fails
        }
    }

    // Write customer data to file and append (do not overwrite)
    public void writeToFile(File file, List<Customer> customers) {
        try {
            // Ensure the file exists, create if not
            if (!file.exists()) {
                File parent = file.getParentFile();
                if (parent != null) {
                    parent.mkdirs();
                }
                file.createNewFile();
            }

            // Append to the file (true for appending)
            try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))) { // 'true' allows appending
                for (Customer customer : customers) {
                    pw.println(customer.toString());
                }
                System.out.println("Data appended to file: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    // Add a new customer to the list and append to file
    public void addCust(Customer p) {
        customersList.add(p);
        writeToFile(CustFile, customersList);  // Append the updated customer list to the file
    }

    public static String generateNextCustomerId() {
    // Initialize with 0 so if no customers exist, we start with 1
    int maxId = 0;
    
    // Read all existing customers to find the highest ID
    if (CustFile.exists()) {
        try (Scanner customerFile = new Scanner(CustFile)) {
            while (customerFile.hasNext()) {
                String[] data = customerFile.nextLine().split(":");
                try {
                    int currentId = Integer.parseInt(data[0]);
                    if (currentId > maxId) {
                        maxId = currentId;
                    }
                } catch (NumberFormatException e) {
                    // Skip if the ID is not a number
                    continue;
                }
            }
        } catch (IOException ioe) {
            // Handle error if file reading fails
            ioe.printStackTrace();
        }
    }
    
    // Return the next ID (maxId + 1) as a string
    return String.valueOf(maxId + 1);
}
    
}
