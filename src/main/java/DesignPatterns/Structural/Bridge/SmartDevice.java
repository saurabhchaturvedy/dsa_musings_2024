package DesignPatterns.Structural.Bridge;

abstract class SmartDevice {
    protected CommunicationProtocol communicationProtocol;

    protected SmartDevice(CommunicationProtocol communicationProtocol) {
        this.communicationProtocol = communicationProtocol;
    }

    public abstract void control();
}
