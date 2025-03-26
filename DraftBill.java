import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class DraftBillSystem {
    private Map<String, String> billDrafts = new HashMap<>(); // Stores service details per customer phone
    
    public void createDraftBill(String phoneNumber, String serviceDetails) {
        billDrafts.put(phoneNumber, serviceDetails);
        System.out.println("Draft bill created for customer with phone number: " + phoneNumber);
    }
    
    public void displayDraftBill(String phoneNumber) {
        if (billDrafts.containsKey(phoneNumber)) {
            System.out.println("Draft Bill for " + phoneNumber + ": " + billDrafts.get(phoneNumber));
        } else {
            System.out.println("No draft bill found for this customer.");
        }
    }
}

public class AutoRepairShopSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DraftBillSystem billSystem = new DraftBillSystem();
        
        while (true) {
            System.out.println("\n1. Create Draft Bill\n2. Display Draft Bill\n3. Exit");
            System.out.print("Select an option: ");
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    System.out.print("Enter Customer Phone Number: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter Service and Inventory Details: ");
                    String details = scanner.nextLine();
                    billSystem.createDraftBill(phone, details);
                    break;
                case "2":
                    System.out.print("Enter Customer Phone Number: ");
                    phone = scanner.nextLine();
                    billSystem.displayDraftBill(phone);
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
