class LoyaltyCard {
    private String cardId;
    private String customer;
    private String dateIssued;
    private double discountAvailable;
    private int stamps;

    public LoyaltyCard(String cardId, String customer, String dateIssued) {
        this.cardId = cardId;
        this.customer = customer;
        this.dateIssued = dateIssued;
        this.discountAvailable = 0.0;
        this.stamps = 0;
    }

    // Getters and setters
    public String getCardId() { return cardId; }
    public String getCustomer() { return customer; }
    public String getDateIssued() { return dateIssued; }
    public double getDiscountAvailable() { return discountAvailable; }
    public int getStamps() { return stamps; }

    public void setDiscountAvailable(double discountAvailable) { 
        this.discountAvailable = discountAvailable; 
    }

    // Add a stamp to the loyalty card
    public void addStamp() {
        this.stamps++;
    }

    // Check if card has enough stamps for discount
    public boolean hasEnoughStamps() {
        return this.stamps >= 8;
    }

    @Override
    public String toString() {
        return cardId + ":" + customer + ":" + dateIssued + ":" + discountAvailable + ":" + stamps;
    }
}