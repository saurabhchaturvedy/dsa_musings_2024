package DesignPatterns.Structural.Bridge;

class SmartLight extends SmartDevice {
    public SmartLight(CommunicationProtocol communicationProtocol) {
        super(communicationProtocol);
    }

    @Override
    public void control() {
        System.out.println("Controlling Smart Light...");
        communicationProtocol.sendSignal("turn on/off");
    }
}