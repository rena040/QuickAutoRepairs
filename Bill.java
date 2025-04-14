import java.util.List;
import java.util.ArrayList;
import java.util.Date;

// Bill class
class Bill {
    private String billId;
    private Customer customer;
    private List<Service> services;
    private double amount;
    private String paymentStatus;

    public Bill(String billId, Customer customer, double amount) {
        this.billId = billId;
        this.customer = customer;
        this.amount = amount;
        this.services = new ArrayList<>();
        this.paymentStatus = "Pending";
    }

    // Getters and setters
    public String getBillId() { return billId; }
    public Customer getCustomer() { return customer; }
    public List<Service> getServices() { return services; }
    public double getAmount() { return amount; }
    public String getPaymentStatus() { return paymentStatus; }

    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    public void addService(Service service) { services.add(service); }
}