package Atlassian.PostKarat18Oct.CostExplorer.CostExplorerBasic;

public class Customer {


    String customerId;

    String productName;

    Subscription subscription;

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
