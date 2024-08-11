package DesignPatterns.Structural.Bridge;

class Bluetooth implements CommunicationProtocol {
    @Override
    public void sendSignal(String action) {
        System.out.println("Sending signal via Bluetooth to " + action);
    }
}