import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Cashier {
    private String cashierId;
    private String name;
    private double workingHours;
    private double payRate;

    // File for storing cashier data
    private static final File CASHIER_FILE = new File("cashier.txt");
    private static List<Cashier> cashiers = new ArrayList<>();

    // Constructors
    public Cashier(String cashierId, String name, double workingHours, double payRate) {
        this.cashierId = cashierId;
        this.name = name;
        this.workingHours = workingHours;
        this.payRate = payRate;
    }

    public Cashier() {}

    // Getters and setters
    public String getCashierId() { return cashierId; }
    public String getName() { return name; }
    public double getWorkingHours() { return workingHours; }
    public double getPayRate() { return payRate; }

    public void setCashierId(String cashierId) { this.cashierId = cashierId; }
    public void setName(String name) { this.name = name; }
    public void setWorkingHours(double workingHours) { this.workingHours = workingHours; }
    public void setPayRate(double payRate) { this.payRate = payRate; }

    // Calculate weekly salary
    public double calculateWeeklySalary() {
        return workingHours * payRate;
    }

    @Override
    public String toString() {
        return cashierId + ":" + name + ":" + workingHours + ":" + payRate;
    }

    // File operations
    public static void readData() {
        cashiers.clear();
        if (!CASHIER_FILE.exists()) return;

        try (Scanner scanner = new Scanner(CASHIER_FILE)) {
            while (scanner.hasNext()) {
                String[] data = scanner.nextLine().split(":");
                if (data.length >= 4) {
                    Cashier cashier = new Cashier(
                        data[0], 
                        data[1], 
                        Double.parseDouble(data[2]), 
                        Double.parseDouble(data[3])
                    );
                    cashiers.add(cashier);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading cashier file: " + e.getMessage());
        }
    }

    public static void writeToFile() {
        try {
            if (!CASHIER_FILE.exists()) {
                CASHIER_FILE.getParentFile().mkdirs();
                CASHIER_FILE.createNewFile();
            }

            try (PrintWriter pw = new PrintWriter(new FileWriter(CASHIER_FILE))) {
                for (Cashier cashier : cashiers) {
                    pw.println(cashier.toString());
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing to cashier file: " + e.getMessage());
        }
    }

    // CRUD operations
    public void addCashier(Cashier cashier) {
        readData(); // Ensure we have current data
        cashiers.add(cashier);
        writeToFile();
    }

    public static Cashier findCashierById(String cashierId) {
        readData();
        for (Cashier cashier : cashiers) {
            if (cashier.getCashierId().equalsIgnoreCase(cashierId)) {
                return cashier;
            }
        }
        return null;
    }

    public static List<Cashier> getAllCashiers() {
        readData();
        return new ArrayList<>(cashiers);
    }

    public static boolean updateCashier(String cashierId, String name, double workingHours, double payRate) {
        readData();
        for (Cashier cashier : cashiers) {
            if (cashier.getCashierId().equalsIgnoreCase(cashierId)) {
                cashier.setName(name);
                cashier.setWorkingHours(workingHours);
                cashier.setPayRate(payRate);
                writeToFile();
                return true;
            }
        }
        return false;
    }

    public static boolean deleteCashier(String cashierId) {
        readData();
        for (int i = 0; i < cashiers.size(); i++) {
            if (cashiers.get(i).getCashierId().equalsIgnoreCase(cashierId)) {
                cashiers.remove(i);
                writeToFile();
                return true;
            }
        }
        return false;
    }

    // ID generation similar to Appointment class
    public static String generateNextCashierId() {
        readData();
        int maxId = 0;
        
        for (Cashier cashier : cashiers) {
            String idStr = cashier.getCashierId();
            if (idStr.startsWith("CSH")) {
                try {
                    int num = Integer.parseInt(idStr.substring(3));
                    if (num > maxId) {
                        maxId = num;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid cashier ID format: " + idStr);
                }
            }
        }
        
        return String.format("CSH%03d", maxId + 1);
    }

    // Additional utility methods
    public static double calculateTotalWeeklyPayroll() {
        readData();
        double total = 0;
        for (Cashier cashier : cashiers) {
            total += cashier.calculateWeeklySalary();
        }
        return total;
    }

    public static List<Cashier> findCashiersWithMinimumHours(double minHours) {
        readData();
        List<Cashier> result = new ArrayList<>();
        for (Cashier cashier : cashiers) {
            if (cashier.getWorkingHours() >= minHours) {
                result.add(cashier);
            }
        }
        return result;
    }
}