package Atlassian.PostKarat18Oct.CostExplorer.Trial;

class Customer {
    private String customerId;
    private String productName;
    Subscription subscription;

    // Constructors, getters, and setters
    public Customer(String customerId, String productName, Subscription subscription) {
        this.customerId = customerId;
        this.productName = productName;
        this.subscription = subscription;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getProductName() {
        return productName;
    }

    public Subscription getSubscription() {
        return subscription;
    }
}