package DesignPatterns.Structural.Bridge;

class SmartThermostat extends SmartDevice {
    public SmartThermostat(CommunicationProtocol communicationProtocol) {
        super(communicationProtocol);
    }

    @Override
    public void control() {
        System.out.println("Controlling Smart Thermostat...");
        communicationProtocol.sendSignal("set temperature");
    }
}