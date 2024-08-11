package DesignPatterns.Structural.Facade;

public class FinancialSystemFacade {


    BillingSystem billingSystem;

    CustomerInvoiceSystem customerInvoiceSystem;


    public void createInvoice(int amount) {
        billingSystem = new BillingSystem();
        Bill bill = billingSystem.createBill(amount);
        customerInvoiceSystem.createInvoiceForTheBill(bill);
    }


    public void setBillingSystem(BillingSystem billingSystem) {
        this.billingSystem = billingSystem;
    }

    public void setCustomerInvoiceSystem(CustomerInvoiceSystem customerInvoiceSystem) {
        this.customerInvoiceSystem = customerInvoiceSystem;
    }
}