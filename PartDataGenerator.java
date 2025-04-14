import java.io.*;
import java.util.*;

public class PartDataGenerator {

    public static void main(String[] args) {
        List<Part> parts = Arrays.asList(
            new Part("P001", "Brake Pads", 35.99, 100, "AutoZone"),
            new Part("P002", "Oil Filter", 8.49, 200, "NAPA Auto Parts"),
            new Part("P003", "Air Filter", 12.75, 150, "Bosch"),
            new Part("P004", "Spark Plug", 6.99, 300, "NGK"),
            new Part("P005", "Battery", 89.99, 50, "DieHard"),
            new Part("P006", "Headlight Bulb", 14.99, 120, "Philips"),
            new Part("P007", "Windshield Wiper", 9.95, 180, "RainX"),
            new Part("P008", "Radiator", 139.99, 30, "Denso"),
            new Part("P009", "Timing Belt", 59.50, 60, "Gates"),
            new Part("P010", "Alternator", 120.00, 25, "Bosch"),
            new Part("P011", "Fuel Pump", 78.25, 40, "Delphi"),
            new Part("P012", "Thermostat", 19.99, 80, "Stant"),
            new Part("P013", "Control Arm", 69.95, 35, "Moog"),
            new Part("P014", "Shock Absorber", 55.00, 70, "KYB"),
            new Part("P015", "Tail Light", 32.50, 45, "ACDelco"),
            new Part("P016", "Catalytic Converter", 210.00, 10, "Walker"),
            new Part("P017", "Muffler", 85.49, 28, "MagnaFlow"),
            new Part("P018", "Oxygen Sensor", 48.75, 90, "Bosch"),
            new Part("P019", "CV Joint", 96.60, 22, "GSP"),
            new Part("P020", "Power Steering Pump", 110.25, 15, "Cardone")
        );

        File file = new File("parts.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Part part : parts) {
                writer.write(part.toString());
                writer.newLine();
            }
            System.out.println("✅ Successfully wrote 20 parts to parts.txt");
        } catch (IOException e) {
            System.out.println("❌ Error writing to file: " + e.getMessage());
        }
    }
}
