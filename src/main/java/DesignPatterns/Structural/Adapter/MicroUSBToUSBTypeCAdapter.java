package DesignPatterns.Structural.Adapter;

class MicroUSBToUSBTypeCAdapter implements USBTypeC {
    private MicroUSBCharger microUSBCharger;

    public MicroUSBToUSBTypeCAdapter(MicroUSBCharger microUSBCharger) {
        this.microUSBCharger = microUSBCharger;
    }

    @Override
    public void chargeWithUSBC() {
        // The adapter translates the USB-C request to a Micro-USB action
        microUSBCharger.chargeWithMicroUSB();
    }
}
