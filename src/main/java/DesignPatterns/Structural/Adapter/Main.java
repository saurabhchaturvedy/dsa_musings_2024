package DesignPatterns.Structural.Adapter;

public class Main {
    public static void main(String[] args) {
        // Old charger
        MicroUSBCharger oldCharger = new MicroUSBCharger();

        // Adapter to use old charger with new phone
        USBTypeC adapter = new MicroUSBToUSBTypeCAdapter(oldCharger);

        // Charging new phone with old charger using the adapter
        adapter.chargeWithUSBC(); // Output: Charging with Micro-USB
    }
}
