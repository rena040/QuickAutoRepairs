import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

class Inventory {
    private List<Part> parts;
    private String storageLocation;
    private File inventoryFile;

    // Constructor without inventoryId
    public Inventory(String storageLocation, String filePath) {
        this.storageLocation = storageLocation;
        this.parts = new ArrayList<>();
        this.inventoryFile = new File(filePath);
        readData(inventoryFile); // Read existing parts from file
    }

    // Default constructor
    public Inventory(String filePath) {

    }

    public Inventory() {
        //TODO Auto-generated constructor stub
    }

    // Getters and setters
    public List<Part> getParts() { return parts; }
    public String getStorageLocation() { return storageLocation; }

    public void addPart(Part part) {
        parts.add(part);
        writeToFile(); // Append the new part to file
    }

    public void removePart(Part part) {
        parts.remove(part);
        writeToFile(); // Rewrite parts to file after removal
    }

    // Method to read existing parts data from file
    public void readData(File filename) {
        try (Scanner partFile = new Scanner(filename)) {
            while (partFile.hasNext()) {
                String data = partFile.nextLine();
                parts.add(Part.fromString(data)); // Create Part object from file line
            }
        } catch (IOException ioe) {
            // Handle error if file reading fails
            System.out.println("Error reading from file.");
        }
    }

    // Method to write parts data to file (appends)
    public void writeToFile() {
        try {
            // Ensure the file exists, create if not
            if (!inventoryFile.exists()) {
                File parent = inventoryFile.getParentFile();
                if (parent != null) {
                    parent.mkdirs();
                }
                inventoryFile.createNewFile();
            }

            // Append to the file (true for appending)
            try (PrintWriter pw = new PrintWriter(new FileWriter(inventoryFile, true))) {
                for (Part part : parts) {
                    pw.println(part.toString());
                }
                System.out.println("Data appended to file: " + inventoryFile.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    // Method to view all parts in the inventory (read from file and display)
    public List<Part> viewAllParts() {
        List<Part> partList = new ArrayList<>(); // To store all parts
        
        // Read and store parts from the file
        try (Scanner partFile = new Scanner(inventoryFile)) {
            if (!partFile.hasNext()) {
                System.out.println("No parts available in inventory.");
            } else {
                System.out.println("Parts in Inventory:");
                while (partFile.hasNext()) {
                    String data = partFile.nextLine();
                    Part part = Part.fromString(data); // Convert the line to a Part object
                    partList.add(part); // Store the part in the list
                    System.out.println(part.toString()); // Optionally print the part details
                }
            }
        } catch (IOException ioe) {
            System.out.println("Error reading parts from file.");
            ioe.printStackTrace();
        }
        
        return partList; // Return the list of parts
    }

    public Part findPart(String identifier) {
        for (Part part : parts) {
            // Assuming Part has getName() and getId() methods
            if (part.getName().equalsIgnoreCase(identifier) || part.getPartId().equals(identifier)) {
                return part; // Return the found part
            }
        }
        System.out.println("Part not found: " + identifier);
        return null; // Return null if part not found
    }

    public void updateInventoryFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(inventoryFile, false))) { // false = overwrite
            for (Part part : parts) {
                pw.println(part.toString());
            }
            System.out.println("Inventory file updated.");
        } catch (IOException e) {
            System.out.println("An error occurred while updating the inventory file.");
            e.printStackTrace();
        }
    }
}
