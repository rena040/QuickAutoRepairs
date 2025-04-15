import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Payment {
    private String paymentId;
    private String appointmentId;
    private String customerId;
    private double amount;
    private String paymentDate;
    private String cashierId;
    private boolean discountApplied;
    private double discountAmount;

    private static final File PAYMENT_FILE = new File("payment.txt");
    private static List<Payment> payments = new ArrayList<>();

    public Payment(String paymentId, String appointmentId, String customerId, 
                  double amount, String paymentDate, String cashierId) {
        this.paymentId = paymentId;
        this.appointmentId = appointmentId;
        this.customerId = customerId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.cashierId = cashierId;
        this.discountApplied = false;
        this.discountAmount = 0.0;
    }

    public Payment(String paymentId, String appointmentId, String customerId, 
                 double amount, String paymentDate, String cashierId,
                 boolean discountApplied, double discountAmount) {
        this(paymentId, appointmentId, customerId, amount, paymentDate, cashierId);
        this.discountApplied = discountApplied;
        this.discountAmount = discountAmount;
    }

    public Payment() {}

    public String getPaymentId() { return paymentId; }
    public String getAppointmentId() { return appointmentId; }
    public String getCustomerId() { return customerId; }
    public double getAmount() { return amount; }
    public String getPaymentDate() { return paymentDate; }
    public String getCashierId() { return cashierId; }
    public boolean isDiscountApplied() { return discountApplied; }
    public double getDiscountAmount() { return discountAmount; }

    public void setDiscountApplied(boolean discountApplied) { this.discountApplied = discountApplied; }
    public void setDiscountAmount(double discountAmount) { this.discountAmount = discountAmount; }

    @Override
    public String toString() {
        return paymentId + ":" + appointmentId + ":" + customerId + ":" + 
               amount + ":" + paymentDate + ":" + cashierId + ":" + 
               discountApplied + ":" + discountAmount;
    }

    public String generateReceipt() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("=== PAYMENT RECEIPT ===\n");
        receipt.append("Payment ID: ").append(paymentId).append("\n");
        receipt.append("Appointment ID: ").append(appointmentId).append("\n");
        receipt.append("Customer ID: ").append(customerId).append("\n");
        receipt.append("Payment Date: ").append(paymentDate).append("\n");
        receipt.append("Cashier ID: ").append(cashierId).append("\n");
        
        if (discountApplied) {
            receipt.append("Discount Applied: $").append(String.format("%.2f", discountAmount)).append("\n");
        }
        
        receipt.append("Total Amount Paid: $").append(String.format("%.2f", amount)).append("\n");
        receipt.append("Status: Payment Successful!\n");
        
        return receipt.toString();
    }

    public static void readData() {
        payments.clear();
        if (!PAYMENT_FILE.exists()) return;

        try (Scanner scanner = new Scanner(PAYMENT_FILE)) {
            while (scanner.hasNext()) {
                String[] data = scanner.nextLine().split(":");
                if (data.length >= 8) {
                    Payment payment = new Payment(
                        data[0], 
                        data[1], 
                        data[2], 
                        Double.parseDouble(data[3]), 
                        data[4], 
                        data[5],
                        Boolean.parseBoolean(data[6]),
                        Double.parseDouble(data[7])
                    );
                    payments.add(payment);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading payment file: " + e.getMessage());
        }
    }

    public static void writeToFile() {
        try {
            if (!PAYMENT_FILE.exists()) {
                PAYMENT_FILE.getParentFile().mkdirs();
                PAYMENT_FILE.createNewFile();
            }

            try (PrintWriter pw = new PrintWriter(new FileWriter(PAYMENT_FILE))) {
                for (Payment payment : payments) {
                    pw.println(payment.toString());
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing to payment file: " + e.getMessage());
        }
    }

    public static void addPayment(Payment payment) {
        readData();
        payments.add(payment);
        writeToFile();
    }

    public static String generateNextPaymentId() {
        readData();
        int maxId = 0;
        
        for (Payment payment : payments) {
            String idStr = payment.getPaymentId();
            if (idStr.startsWith("PAY")) {
                try {
                    int num = Integer.parseInt(idStr.substring(3));
                    if (num > maxId) {
                        maxId = num;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid payment ID format: " + idStr);
                }
            }
        }
        
        return String.format("PAY%03d", maxId + 1);
    }

    /**
     * Process payment by searching for appointments by customer ID
     * @param customerId The customer ID to search for
     * @return List of scheduled appointments for the customer
     */
    public List<Appointment> processPaymentByCustomerId(String customerId) {
        PaymentProcessor processor = new PaymentProcessor();
        return processor.findScheduledAppointmentsByCustomerId(customerId);
    }
    
    /**
     * Process payment by searching for an appointment ID
     * @param appointmentId The appointment ID to process
     * @return The appointment if found and in "Scheduled" status, null otherwise
     */
    public Appointment processPaymentByAppointmentId(String appointmentId) {
        PaymentProcessor processor = new PaymentProcessor();
        Appointment appointment = processor.findAppointmentById(appointmentId);
        
        if (appointment == null) {
            return null;
        }
        
        if (!"Scheduled".equalsIgnoreCase(appointment.getStatus())) {
            return null;
        }
        
        return appointment;
    }
    
    /**
     * Process payment for a specific appointment
     * @param appointmentId The appointment ID to process payment for
     * @param cashierId The cashier ID processing the payment
     * @param applyDiscount Whether to apply a loyalty discount
     * @return The receipt as a formatted string
     */
    public String processPayment(String appointmentId, String cashierId, boolean applyDiscount) {
        PaymentProcessor processor = new PaymentProcessor();
        
        Appointment appointment = processor.findAppointmentById(appointmentId);
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment not found");
        }
        
        Payment payment = processor.processAppointmentPayment(appointment, cashierId, applyDiscount);
        if (payment == null) {
            throw new IllegalArgumentException("Payment processing failed");
        }
        
        return payment.generateReceipt();
    }
    
    /**
     * Get customer by ID
     * @param customerId The customer ID
     * @return The customer object
     */
    public Customer getCustomerById(String customerId) {
        PaymentProcessor processor = new PaymentProcessor();
        return processor.getCustomerById(customerId);
    }

    public Appointment getAppointmentById(String appointmentId) {
        PaymentProcessor processor = new PaymentProcessor();
        return processor.getAppointmentById(appointmentId);
    }
}

class PaymentProcessor {
    private Appointment appointmentManager;
    private Customer customerManager;

    public PaymentProcessor() {
        this.appointmentManager = new Appointment();
        this.customerManager = new Customer();
        
        appointmentManager.readData(new File("appointment.txt"));
        customerManager.readData(new File("customer.txt"));
    }

    /**
     * Find an appointment by its ID
     * @param appointmentId The ID of the appointment to find
     * @return The appointment if found, null otherwise
     */
    public Appointment findAppointmentById(String appointmentId) {
        appointmentManager.readData(new File("appointment.txt"));
        
        for (Appointment appointment : appointmentManager.appointments) {
            if (appointment.getAppointmentId().equals(appointmentId)) {
                return appointment;
            }
        }
        return null;
    }

    /**
     * Find all appointments for a customer with status "Scheduled"
     * @param customerId The ID of the customer
     * @return List of scheduled appointments for the customer
     */
    public List<Appointment> findScheduledAppointmentsByCustomerId(String customerId) {
        appointmentManager.readData(new File("appointment.txt"));
        List<Appointment> scheduledAppointments = new ArrayList<>();
        
        for (Appointment appointment : appointmentManager.appointments) {
            if (appointment.getCustomerId().equals(customerId) && 
                "Scheduled".equalsIgnoreCase(appointment.getStatus())) {
                scheduledAppointments.add(appointment);
            }
            System.err.println("Appointment ID: " + appointment.getAppointmentId() + 
                               ", Status: " + appointment.getStatus() + 
                               ", Customer ID: " + appointment.getCustomerId());
        }
        return scheduledAppointments;
    }

    /**
     * Process payment for an appointment and update its status
     * @param appointment The appointment to process payment for
     * @param cashierId The ID of the cashier processing the payment
     * @param applyDiscount Whether to apply a loyalty discount
     * @return The created payment record
     */
    /**
 * Process payment for an appointment and update its status
 * @param appointment The appointment to process payment for
 * @param cashierId The ID of the cashier processing the payment
 * @param applyDiscount Whether to apply a loyalty discount
 * @return The created payment record
 */
public Payment processAppointmentPayment(Appointment appointment, String cashierId, boolean applyDiscount) {
    if (appointment == null) {
        return null;
    }

    appointmentManager.readData(new File("appointment.txt"));
    
    Appointment currentAppointment = null;
    for (Appointment app : appointmentManager.appointments) {
        if (app.getAppointmentId().equals(appointment.getAppointmentId())) {
            currentAppointment = app;
            break;
        }
    }
    
    if (currentAppointment == null) {
        return null;
    }

    if (!currentAppointment.hasPaid()) {
        System.out.println("Failed to process payment - appointment cannot be marked as paid");
        return null;
    }

    double amount = currentAppointment.getDraft();
    double discountAmount = 0.0;

    Customer customer = getCustomerById(currentAppointment.getCustomerId());
    if (applyDiscount && customer != null && 
        customer.getLoyaltyCard() != null && 
        customer.getLoyaltyCard().getStamps() >= 8) {
        
        double discountRate = 0.10;
        discountAmount = amount * discountRate;
        amount -= discountAmount;
    }

    String paymentId = Payment.generateNextPaymentId();
    String paymentDate = LocalDate.now().format(Appointment.DATE_FORMATTER);
    
    Payment payment = new Payment(
        paymentId,
        currentAppointment.getAppointmentId(),
        currentAppointment.getCustomerId(),
        amount,
        paymentDate,
        cashierId,
        applyDiscount,
        discountAmount
    );
    
    appointmentManager.writeToFile(new File("appointment.txt"), appointmentManager.appointments);
    Payment.addPayment(payment);
    
    return payment;
}
    /**
     * Get customer details by ID
     * @param customerId The ID of the customer
     * @return The customer object if found, null otherwise
     */
    public Customer getCustomerById(String customerId) {
        customerManager.readData(new File("customer.txt")); // Ensure latest data
        for (Customer customer : customerManager.customersList) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }
    public Appointment getAppointmentById(String appointmentId) {
        for (Appointment appointment : appointmentManager.appointments) {
            if (appointment.getAppointmentId().equals(appointmentId)) {
                return appointment;
            }
        }
        return null;
    }
}