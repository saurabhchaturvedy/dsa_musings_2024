package DesignPatterns.Structural.Facade;

public class BillingSystem {


    Bill createBill(int amount) {
        return new Bill(amount);
    }
}