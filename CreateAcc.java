import java.util.HashMap;
import java.util.Scanner;

class CustomerAccountSystem {
    private HashMap<String, String> customers = new HashMap<>();
    
    public void createAccount(String adminName) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Admin " + adminName + " is creating a new account.");
        
        System.out.print("Enter Customer Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Customer Phone Number: ");
        String phone = scanner.nextLine();
        
        if (customers.containsKey(phone)) {
            System.out.println("Error: An account with this phone number already exists!");
        } else {
            customers.put(phone, name);
            System.out.println("Success: Account created for " + name + " with phone number " + phone + ".");
        }
    }
    
    public void displayCustomers() {
        System.out.println("\nRegistered Customers:");
        for (String phone : customers.keySet()) {
            System.out.println("Name: " + customers.get(phone) + ", Phone: " + phone);
        }
    }
    
    public static void main(String[] args) {
        CustomerAccountSystem system = new CustomerAccountSystem();
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter Admin Name: ");
        String adminName = scanner.nextLine();
        
        while (true) {
            System.out.println("\n1. Create Account\n2. Display Customers\n3. Exit");
            System.out.print("Select an option: ");
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    system.createAccount(adminName);
                    break;
                case "2":
                    system.displayCustomers();
                    break;
                case "3":
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
