package DesignPatterns.Structural.Bridge;

class WiFi implements CommunicationProtocol {
    @Override
    public void sendSignal(String action) {
        System.out.println("Sending signal via WiFi to " + action);
    }
}