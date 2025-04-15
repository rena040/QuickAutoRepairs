import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Appointment {
    private String appointmentId;
    private String customerId;
    private String mechanicId;
    private String vehicleName;
    private String appointmentDate;
    private String appointmentTime; 
    private String service;
    private String status;
    private double draft;
    private boolean paid;  

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    
    File AFile = new File("appointment.txt");
    List<Appointment> appointments = new ArrayList<>();

    public void BookAppointment(String appId, String custId, String mechanicId, String vehicle, String date, String time, String serviceId) {
        this.appointmentId = appId;
        this.customerId = custId;
        this.mechanicId = mechanicId;    
        this.vehicleName = vehicle;
        this.appointmentDate = date;
        this.appointmentTime = time;
        this.service = serviceId;
        this.status = "Scheduled"; // Default status when booking an appointment
        this.draft = Service.findServiceById(serviceId).calculateTotalCost(Mechanic.searchMechanicById(mechanicId).getPayRate()); // Default draft value
        this.paid = false; // Default to not paid when creating appointment
    }

    public Appointment(String appointmentId, String customerId, String mechanicId, 
                      String vehicleName, String appointmentDate, String appointmentTime,
                      String service, String status, double draft, boolean paid) {
        this.appointmentId = appointmentId;
        this.customerId = customerId;
        this.mechanicId = mechanicId;
        this.vehicleName = vehicleName;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.service = service;
        this.status = status;
        this.draft = draft;
        this.paid = paid;
    }

    public Appointment() {}

    public String getAppointmentId() { return appointmentId; }
    public String getCustomerId() { return customerId; }
    public String getMechanicId() { return mechanicId; }
    public String getVehicleName() { return vehicleName; }
    public String getAppointmentDate() { return appointmentDate; }
    public String getAppointmentTime() { return appointmentTime; }
    public String getStatus() { return status; }
    public String getService() { return service; }
    public void setStatus(String status) { 
        this.status = status; 
        if ("Completed".equalsIgnoreCase(status)) {
            this.paid = true;
        }
    }
    public double getDraft() { return draft; }
    public void setDraft(double draft) { this.draft = draft; }
    public boolean isPaid() { return paid; }
    public void setPaid(boolean paid) { this.paid = paid; }

    public boolean hasPassed() {
        try {
            LocalDate appointmentLocalDate = LocalDate.parse(this.appointmentDate, DATE_FORMATTER);
            return appointmentLocalDate.isBefore(LocalDate.now());
        } catch (DateTimeParseException e) {
            System.err.println("Invalid date format: " + this.appointmentDate);
            return false; // Consider invalid dates as not passed
        }
    }
    public void completeService() {
        Service service = Service.findServiceById(this.service);
        if (service != null) {
            if (service.completeService()) {
                this.status = "Completed";
                writeToFile(AFile, appointments);
                System.out.println("Service completed successfully");
            } else {
                System.out.println("Could not complete service - parts unavailable");
            }
        } else {
            System.out.println("Service not found");
        }
    }

    public void updateStatusBasedOnDate() {
        if (hasPassed()) {
            this.status = "Completed";
            this.paid = true; // Automatically mark as paid when completed
        } else {
            this.status = "Scheduled";
        }
    }

    public boolean hasPaid() {
        if (!"Scheduled".equalsIgnoreCase(this.status)) {
            System.out.println("Cannot mark as paid - appointment is not in Scheduled status");
            return false;
        }
        
        this.status = "Paid";
        this.paid = true;
        return true;
    }

    public void updateStatus(String status) {
        this.status = status;
        if ("Completed".equalsIgnoreCase(status)) {
            this.paid = true;
        }
        writeToFile(AFile, appointments);
    }

    @Override
public String toString() {
    String formattedTime = appointmentTime.replace(":", "-"); // Replace colon with hyphen
    return String.join(":",
            appointmentId,
            customerId,
            mechanicId,
            vehicleName,
            appointmentDate,
            formattedTime,
            service,
            status,
            String.valueOf(draft),
            String.valueOf(paid));
}

    public void readData(File filename) {
        appointments.clear();
        if (!filename.exists()) return;
    
        try (Scanner appFile = new Scanner(filename)) {
            while (appFile.hasNext()) {
                String line = appFile.nextLine();
                String[] data = line.split(":");
                if (data.length >= 10) {
                    String appointmentTime = data[5].replace("-", ":"); // Revert hyphen to colon
                    boolean paidStatus = Boolean.parseBoolean(data[9]);
                    Appointment p = new Appointment(
                            data[0], data[1], data[2], data[3], data[4],
                            appointmentTime, data[6], data[7],
                            Double.parseDouble(data[8]), paidStatus);
                    p.updateStatusBasedOnDate();
                    appointments.add(p);
                }
            }
        } catch (IOException ioe) {
            System.out.println("Error reading appointment file: " + ioe.getMessage());
        }
    }

    public void writeToFile(File file, List<Appointment> appointmentsList) {
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
                for (Appointment app : appointmentsList) {
                    app.updateStatusBasedOnDate(); 
                    pw.println(app.toString());
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }


    public void addAppointment(Appointment appointment) {
        String custid = appointment.getCustomerId();
        Customer customer = new Customer();
        LoyaltyCard lc = customer. findLoyaltyCardByCustomerId(custid);
        lc.addStamp();
        appointment.updateStatusBasedOnDate();
        appointments.add(appointment);
        writeToFile(AFile, appointments);
    }

    public List<Appointment> searchAppointmentsByCustomerId(String customerId) {
        List<Appointment> matchedAppointments = new ArrayList<>();
        readData(AFile); // Ensure data is loaded

        for (Appointment appointment : appointments) {
            if (appointment.getCustomerId().equals(customerId)) {
                matchedAppointments.add(appointment);
            }
            
        }
        return matchedAppointments;
    }

    public static String generateNextAppointmentId() {
        File file = new File("appointment.txt");
        int maxId = 0;
        
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.contains(":")) {
                        String idStr = line.split(":")[0];
                        if (idStr.startsWith("APT")) {
                            try {
                                int num = Integer.parseInt(idStr.substring(3));
                                if (num > maxId) {
                                    maxId = num;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid appointment ID format: " + idStr);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading appointment file: " + e.getMessage());
            }
        }
        
        return String.format("APT%03d", maxId + 1);
    }

    public static Appointment[] getAllAppointments() {
        throw new UnsupportedOperationException("Unimplemented method 'getAllAppointments'");
    }

    public boolean checkAvailability(String mechanicId, String date, String time) {
        readData(AFile);

        // Parse the requested time into a LocalTime object
        LocalTime requestedTime;
        try {
            requestedTime = LocalTime.parse(time, TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            System.err.println("Invalid time format: " + time);
            return false; // Invalid time format
        }

        // Iterate through the appointments to check for conflicts
        for (Appointment appointment : appointments) {
            if (appointment.getMechanicId().equals(mechanicId) &&
                appointment.getAppointmentDate().equals(date)) {
                
                // Parse the existing appointment time
                LocalTime existingTime;
                try {
                    existingTime = LocalTime.parse(appointment.getAppointmentTime(), TIME_FORMATTER);
                } catch (DateTimeParseException e) {
                    System.err.println("Invalid time format in existing appointment: " + appointment.getAppointmentTime());
                    continue; // Skip invalid times
                }

                // Check if the requested time overlaps with the 3-hour slot of the existing appointment
                if (!requestedTime.isBefore(existingTime.minusHours(3)) && 
                    !requestedTime.isAfter(existingTime.plusHours(3))) {
                    return false; // Overlap detected
                }
            }
        }
        return true; // Mechanic is available
    }
}