package DesignPatterns.Structural.Bridge;

class SmartLock extends SmartDevice {
    public SmartLock(CommunicationProtocol communicationProtocol) {
        super(communicationProtocol);
    }

    @Override
    public void control() {
        System.out.println("Controlling Smart Lock...");
        communicationProtocol.sendSignal("lock/unlock");
    }
}