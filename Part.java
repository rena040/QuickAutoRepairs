class Part {
    private String partId;
    private String name;
    private double price;
    private int quantityInStock;
    private String supplier;

    public Part(String partId, String name, double price, int quantityInStock, String supplier) {
        this.partId = partId;
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.supplier = supplier;
    }

    public Part() {}

    public String getPartId() { return partId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantityInStock() { return quantityInStock; }
    public String getSupplier() { return supplier; }

    public void setPartId(String partId) { this.partId = partId; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setQuantityInStock(int quantityInStock) { this.quantityInStock = quantityInStock; }
    public void setSupplier(String supplier) { this.supplier = supplier; }

    @Override
    public String toString() {
        return partId + ":" + name + ":" + price + ":" + quantityInStock + ":" + supplier;
    }

    public static Part fromString(String data) {
        String[] parts = data.split(":");
        String partId = parts[0];
        String name = parts[1];
        double price = Double.parseDouble(parts[2]);
        int quantityInStock = Integer.parseInt(parts[3]);
        String supplier = parts[4];
        return new Part(partId, name, price, quantityInStock, supplier);
    }
}
