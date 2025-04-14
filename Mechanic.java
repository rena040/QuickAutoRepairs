import java.util.*;
import java.io.*;

public class Mechanic {
    private String mechanicId;
    private String name;
    private String specialization;
    private double workHours;
    private double payRate;

    private static final File MECHANIC_FILE = new File("mechanics.txt");
    private static List<Mechanic> mechanicList = new ArrayList<>();
    private static int lastUsedId = 0; // Track the last used ID number

    public Mechanic() {}

    public Mechanic(String mechanicId, String name, String specialization, double workHours, double payRate) {
        this.mechanicId = mechanicId;
        this.name = name;
        this.specialization = specialization;
        this.workHours = workHours;
        this.payRate = payRate;
    }

    public String getMechanicId() { return mechanicId; }
    public String getName() { return name; }
    public String getSpecialization() { return specialization; }
    public double getWorkHours() { return workHours; }
    public double getPayRate() { return payRate; }

    public void setWorkHours(double workHours) { this.workHours = workHours; }
    public void setPayRate(double payRate) { this.payRate = payRate; }

    @Override
    public String toString() {
        return mechanicId + ":" + name + ":" + specialization + ":" + workHours + ":" + payRate;
    }

    public static String generateNextId() {
        lastUsedId++;
        return String.format("M%03d", lastUsedId);
    }

    public static void loadMechanics() {
        mechanicList.clear();
        lastUsedId = 0;

        if (!MECHANIC_FILE.exists()) return;

        try (Scanner fileScanner = new Scanner(MECHANIC_FILE)) {
            while (fileScanner.hasNextLine()) {
                String[] data = fileScanner.nextLine().split(":");
                if (data.length == 5) {
                    Mechanic m = new Mechanic(data[0], data[1], data[2],
                            Double.parseDouble(data[3]), Double.parseDouble(data[4]));
                    mechanicList.add(m);

                    try {
                        int numericId = Integer.parseInt(data[0].substring(1));
                        if (numericId > lastUsedId) {
                            lastUsedId = numericId;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid mechanic ID format: " + data[0]);
                    }
                }
            }
        } catch (IOException ioe) {
            System.out.println("Error reading mechanics: " + ioe.getMessage());
        }
    }

    public void addMechanic(Mechanic m) {
        mechanicList.add(m);
        try (PrintWriter pw = new PrintWriter(new FileWriter(MECHANIC_FILE, true))) {
            pw.println(m.toString());
        } catch (IOException e) {
            System.out.println("Error writing mechanic to file: " + e.getMessage());
        }
    }

    public static Mechanic searchMechanicById(String id) {
        for (Mechanic m : mechanicList) {
            if (m.getMechanicId().equalsIgnoreCase(id)) {
                return m;
            }
        }
        return null;
    }

    public static String getMechanicIdByName(String name) {
        for (Mechanic m : mechanicList) {
            if (m.getName().equalsIgnoreCase(name)) {
                return m.getMechanicId();
            }
        }
        return null;
    }

    public static List<Mechanic> getAllMechanics() {
        return new ArrayList<>(mechanicList);
    }
public static boolean updateMechanic(Mechanic updatedMechanic) {
    loadMechanics(); // Ensure we have the latest data
    
    boolean found = false;
    for (int i = 0; i < mechanicList.size(); i++) {
        if (mechanicList.get(i).getMechanicId().equals(updatedMechanic.getMechanicId())) {
            mechanicList.set(i, updatedMechanic);
            found = true;
            break;
        }
    }
    
    if (found) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(MECHANIC_FILE))) {
            for (Mechanic m : mechanicList) {
                pw.println(m.toString());
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error updating mechanic: " + e.getMessage());
            return false;
        }
    }
    return false;
}
}
