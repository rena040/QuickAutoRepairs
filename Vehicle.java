import java.util.List;
import java.util.ArrayList;
import java.util.Date;
class Vehicle {
    private String vehicleId;
    private String brand;
    private String model;
    private int year;
    private Customer owner;
    private List<Service> services;
    private String diagnostics;

    public Vehicle(String vehicleId, String brand, String model, int year, Customer owner) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.owner = owner;
        this.services = new ArrayList<>();
    }

    // Getters and setters
    public String getVehicleId() { return vehicleId; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public Customer getOwner() { return owner; }
    public List<Service> getServices() { return services; }
    public String getDiagnostics() { return diagnostics; }

    public void setDiagnostics(String diagnostics) { this.diagnostics = diagnostics; }
    public void addService(Service service) { services.add(service); }
}