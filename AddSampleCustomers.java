import java.util.ArrayList;
import java.util.List;

public class AddSampleCustomers {
    public static void main(String[] args) {
        // Create a list to hold our sample customers
        List<Customer> sampleCustomers = new ArrayList<>();
        
        // Add all the sample customers to the list with their stamp counts
        sampleCustomers.add(createCustomerWithStamps("CST001", "Bob Brown", "555-1234", 5));
        sampleCustomers.add(createCustomerWithStamps("CST002", "Mary Davis", "555-5678", 4));
        sampleCustomers.add(createCustomerWithStamps("CST003", "John Wilson", "555-9012", 4));
        sampleCustomers.add(createCustomerWithStamps("CST004", "Emma Clark", "555-3456", 4));
        sampleCustomers.add(createCustomerWithStamps("CST005", "Oliver Lewis", "555-7890", 4));
        sampleCustomers.add(createCustomerWithStamps("CST006", "Sophie Walker", "555-2345", 4));
        sampleCustomers.add(createCustomerWithStamps("CST007", "Charlie Hall", "555-6789", 3));
        sampleCustomers.add(createCustomerWithStamps("CST008", "Lucy Allen", "555-4567", 3));
        sampleCustomers.add(createCustomerWithStamps("CST009", "George Young", "555-8901", 3));
        sampleCustomers.add(createCustomerWithStamps("CST010", "Mia King", "555-6543", 3));
        
        // Create a Customer instance to use its methods
        Customer customerManager = new Customer();
        
        // Add each customer to the system
        for (Customer customer : sampleCustomers) {
            customerManager.addCust(customer);
            System.out.println("Added customer: " + customer.getCustomerId() + " - " + customer.getName() + 
                             " with " + customer.getLoyaltyCard().getStamps() + " stamps");
        }
        
        System.out.println("\nAll sample customers have been added to the system.");
    }
    
    private static Customer createCustomerWithStamps(String customerId, String name, String phone, int stamps) {
        // Create customer
        Customer customer = new Customer(customerId, name, phone);
        
        // Get the customer's loyalty card
        LoyaltyCard loyaltyCard = customer.getLoyaltyCard();
        
        // Add the specified number of stamps
        for (int i = 0; i < stamps; i++) {
            loyaltyCard.addStamp();
        }
        
        // Check if they qualify for a discount (8 stamps = 10% discount)
        if (stamps >= 8) {
            loyaltyCard.setDiscountAvailable(10.0);
        }
        
        return customer;
    }
}