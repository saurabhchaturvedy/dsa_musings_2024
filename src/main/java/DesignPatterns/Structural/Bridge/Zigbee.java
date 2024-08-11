package DesignPatterns.Structural.Bridge;

class Zigbee implements CommunicationProtocol {
    @Override
    public void sendSignal(String action) {
        System.out.println("Sending signal via Zigbee to " + action);
    }
}