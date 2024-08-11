package DesignPatterns.Structural.Facade;

public class DesignExecutor {


    public static void main(String[] args) {

        BillingSystem billingSystem = new BillingSystem();
        CustomerInvoiceSystem customerInvoiceSystem = new CustomerInvoiceSystem();

        FinancialSystemFacade financialSystemFacade = new FinancialSystemFacade();
        financialSystemFacade.setBillingSystem(billingSystem);
        financialSystemFacade.setCustomerInvoiceSystem(customerInvoiceSystem);

        financialSystemFacade.createInvoice(3500);
    }
}