import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TestDataGenerator {

    public static void main(String[] args) {
        generateAdminData();
        generateAppointmentData();
        generateCashierData();
        generateCustomerData();
        generateLoyaltyCardData();
        generateMechanicData();
        generatePartData();
        generateServiceData();
    }

    private static void generateAdminData() {
        List<String> admins = Arrays.asList(
            "ADM001:John Doe",
            "ADM002:Jane Smith",
            "ADM003:Robert Johnson",
            "ADM004:Emily Davis",
            "ADM005:Michael Wilson",
            "ADM006:Sarah Brown",
            "ADM007:David Miller",
            "ADM008:Laura Taylor",
            "ADM009:James Anderson",
            "ADM010:Karen Thomas"
        );
        writeToFile("admin.txt", admins);
    }

    private static void generateAppointmentData() {
        List<String> appointments = Arrays.asList(
            "APT001:CST001:M001:Toyota Camry:2025-03-15:Oil Change:Completed:85.0:true",
            "APT002:CST002:M002:Honda Civic:2025-03-20:Tire Rotation:Completed:120.0:true",
            "APT003:CST003:M003:Ford Focus:2025-04-01:Brake Inspection:Completed:150.0:true",
            "APT004:CST004:M004:Chevrolet Malibu:2025-04-05:Battery Replacement:Completed:200.0:true",
            
            "APT005:CST005:M005:Nissan Altima:2025-04-15:Engine Diagnostics:Scheduled:90.0:false",
            "APT006:CST006:M001:Hyundai Ioniq:2025-04-20:Transmission Repair:In Progress:180.0:false",
            "APT007:CST007:M002:Kia EV9:2025-04-25:Suspension Check:Scheduled:220.0:false",
            "APT008:CST008:M003:VW ID.4:2025-05-01:AC Service:Scheduled:160.0:false",
            "APT009:CST009:M004:Subaru Solterra:2025-05-05:Exhaust Repair:Scheduled:250.0:false",
            "APT010:CST010:M005:Mazda CX-90:2025-05-10:Hybrid System Check:Scheduled:300.0:false",
            "APT011:CST001:M006:Toyota bZ4X:2025-05-15:General Maintenance:Scheduled:170.0:false",
            "APT012:CST002:M007:Honda Prologue:2025-05-20:Oil Change:In Progress:210.0:false",
            "APT013:CST003:M008:Ford F-150 Lightning:2025-06-01:Brake Replacement:Scheduled:190.0:false",
            "APT014:CST004:M009:Chevrolet Blazer EV:2025-06-05:Battery Replacement:Scheduled:400.0:false",
            "APT015:CST005:M010:Nissan Ariya:2025-06-10:Engine Diagnostics:Scheduled:280.0:false",
            "APT016:CST006:M006:Hyundai Kona Electric:2025-06-15:Suspension Check:Scheduled:320.0:false",
            "APT017:CST007:M007:Kia Niro EV:2025-06-20:AC Service:Scheduled:350.0:false",
            "APT018:CST008:M008:VW ID.Buzz:2025-07-01:Exhaust Repair:Scheduled:380.0:false",
            "APT019:CST009:M009:Subaru Ascent:2025-07-05:Hybrid System Check:Scheduled:270.0:false",
            "APT020:CST010:M010:Mazda MX-30:2025-07-10:General Maintenance:Scheduled:420.0:false"
        );
        
        writeToFile("appointment.txt", appointments);
    }

    private static void generateCashierData() {
        List<String> cashiers = Arrays.asList(
            "CSH001:Alice Johnson:40.0:15.0",
            "CSH002:Bob Wilson:35.5:18.5",
            "CSH003:Carol White:37.0:16.0",
            "CSH004:David Lee:40.0:20.0",
            "CSH005:Eva Green:38.5:17.5",
            "CSH006:Frank Black:36.0:19.0",
            "CSH007:Grace Hall:39.0:18.0",
            "CSH008:Henry Young:37.5:17.0",
            "CSH009:Ivy King:40.0:16.5",
            "CSH010:Jack Scott:35.0:20.5"
        );
        writeToFile("cashier.txt", cashiers);
    }

    private static void generateCustomerData() {
        List<String> customers = Arrays.asList(
            "CST001:Bob Brown:555-1234",
            "CST002:Mary Davis:555-5678",
            "CST003:John Wilson:555-9012",
            "CST004:Emma Clark:555-3456",
            "CST005:Oliver Lewis:555-7890",
            "CST006:Sophie Walker:555-2345",
            "CST007:Charlie Hall:555-6789",
            "CST008:Lucy Allen:555-4567",
            "CST009:George Young:555-8901",
            "CST010:Mia King:555-6543"
        );
        writeToFile("customer.txt", customers);
    }

    private static void generateLoyaltyCardData() {
        List<String> loyaltyCards = Arrays.asList(
            "LC001:CST001:2023-01-01:10.0:8",
            "LC002:CST002:2023-02-01:0.0:3",
            "LC003:CST003:2023-03-01:0.0:5",
            "LC004:CST004:2023-04-01:5.0:6",
            "LC005:CST005:2023-05-01:0.0:2",
            "LC006:CST006:2023-06-01:15.0:9",
            "LC007:CST007:2023-07-01:0.0:4",
            "LC008:CST008:2023-08-01:10.0:7",
            "LC009:CST009:2023-09-01:0.0:1",
            "LC010:CST010:2023-10-01:20.0:10"
        );
        writeToFile("loyaltycards.txt", loyaltyCards);
    }

    private static void generateMechanicData() {
        List<String> mechanics = Arrays.asList(
            "M001:Mike Jones:Engine:40:20.0",
            "M002:Sarah Lee:Brakes:35:25.0",
            "M003:Tom Brown:Transmission:38:22.0",
            "M004:Emma Wilson:Suspension:37:24.0",
            "M005:James Smith:Electrical:39:21.0",
            "M006:Lily Davis:AC Systems:36:23.0",
            "M007:Ryan Taylor:Exhaust:38:22.5",
            "M008:Chloe Martin:Diagnostics:40:26.0",
            "M009:Ethan Anderson:Hybrid Systems:35:24.5",
            "M010:Zoe Clark:General Maintenance:37:20.5"
        );
        writeToFile("mechanics.txt", mechanics);
    }

    private static void generatePartData() {
        List<String> parts = Arrays.asList(
            "PRT001:Oil Filter:10.0:50:AutoParts Inc",
            "PRT002:Air Filter:15.0:30:AutoParts Inc",
            "PRT003:Brake Pads:60.0:25:BrakeCo",
            "PRT004:Spark Plug:8.0:100:SparkInc",
            "PRT005:Windshield Wiper:12.0:75:RainTech",
            "PRT006:Battery:120.0:20:PowerCell",
            "PRT007:Timing Belt:45.0:40:BeltMasters",
            "PRT008:Oxygen Sensor:85.0:30:SensorPro",
            "PRT009:Fuel Pump:95.0:25:FlowTech",
            "PRT010:Shock Absorber:75.0:35:SuspensionPlus"
        );
        writeToFile("parts.txt", parts);
    }

    private static void generateServiceData() {
        long baseDate = Date.from(LocalDate.of(2023, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime();
        
        List<String> services = Arrays.asList(
            "SRV001:Oil Change:PRT001-1,PRT002-1:Standard Oil Change:" + (baseDate + 86400000 * 15) + ":M001",
            "SRV002:Brake Replacement:PRT003-2:Brake System Overhaul:" + (baseDate + 86400000 * 16) + ":M002",
            "SRV003:Tune Up:PRT004-4:Engine Tune Up:" + (baseDate + 86400000 * 30) + ":M003",
            "SRV004:Battery Replacement:PRT006-1:Battery Installation:" + (baseDate + 86400000 * 45) + ":M004",
            "SRV005:Wiper Replacement:PRT005-2:Wiper Blade Change:" + (baseDate + 86400000 * 60) + ":M005",
            "SRV006:Timing Belt Change:PRT007-1:Timing Belt Service:" + (baseDate + 86400000 * 75) + ":M006",
            "SRV007:Sensor Replacement:PRT008-2:O2 Sensor Change:" + (baseDate + 86400000 * 90) + ":M007",
            "SRV08:Fuel System Service:PRT009-1:Fuel Pump Replacement:" + (baseDate + 86400000 * 105) + ":M008",
            "SRV09:Suspension Repair:PRT010-2:Shock Absorber Change:" + (baseDate + 86400000 * 120) + ":M009",
            "SRV10:AC Service:PRT002-1,PRT005-1:AC System Maintenance:" + (baseDate + 86400000 * 135) + ":M010"
        );
        writeToFile("service.txt", services);
    }

    private static void writeToFile(String filename, List<String> data) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (String line : data) {
                writer.write(line + "\n");
            }
            System.out.println("Generated " + data.size() + " entries in " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to " + filename + ": " + e.getMessage());
        }
    }
}