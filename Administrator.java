import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


class Administrator {
    private String adminId;
    private String name;
    private Inventory inventory;
    private List<Employee> employeeList;
    private List<Customer> customerList;
    List<Administrator> admins = new ArrayList<>();
    File AdminFile = new File("admin.txt");


    public Administrator(String adminId, String name) {
        this.adminId = adminId;
        this.name = name;
        this.employeeList = new ArrayList<>();
        this.customerList = new ArrayList<>();
    }

    public String getAdminId() { return adminId; }
    public String getName() { return name; }
    public Inventory getInventory() { return inventory; }
    public List<Employee> getEmployeeList() { return employeeList; }
    public List<Customer> getCustomerList() { return customerList; }

    public void setInventory(Inventory inventory) { this.inventory = inventory; }
    public void addEmployee(Employee employee) { employeeList.add(employee); }
    public void addCustomer(Customer customer) { customerList.add(customer); }


    @Override
    public String toString() {
        return adminId + ":" + name + ":" + employeeList + ":" + customerList;
    }

    public void readData(File filename) {
        try (Scanner fileScanner = new Scanner(filename)) {
            while (fileScanner.hasNext()) {
                String[] data = fileScanner.nextLine().split(":");
                Administrator p = new Administrator(data[0], data[1]);
                admins.add(p);
            }
        } catch (IOException ioe) {
        }
    }

    public void writeToFile(File file, List<Administrator> admins2) {
        try {
            if (!file.exists()) {
                File parent = file.getParentFile();
                if (parent != null) {
                    parent.mkdirs();
                }
                file.createNewFile();
            }

            try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))) { // 'true' allows appending
                for (Administrator ad : admins2) {
                    pw.println(ad.toString());
                }
                System.out.println("Data appended to file: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public void addAdmin(Administrator ad) {
        admins.add(ad);
        writeToFile(AdminFile, admins);  // Append the updated customer list to the file
    }

    public static String generateNextAdminId() {
        int maxId = 0;

        if (new File("admin.txt").exists()) {
            try (Scanner adminFile = new Scanner(new File("admin.txt"))) {
                while (adminFile.hasNext()) {
                    String[] data = adminFile.nextLine().split(":");
                    try {
                        if (data[0].startsWith("ADM")) {
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
                System.out.println("Error reading admin file: " + ioe.getMessage());
            }
        }

        int nextId = maxId + 1;

        return String.format("ADM%03d", nextId);
    }
}