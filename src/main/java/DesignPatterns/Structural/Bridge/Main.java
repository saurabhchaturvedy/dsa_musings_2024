package DesignPatterns.Structural.Bridge;

public class Main {
    public static void main(String[] args) {
        CommunicationProtocol wifi = new WiFi();
        CommunicationProtocol zigbee = new Zigbee();
        CommunicationProtocol bluetooth = new Bluetooth();

        SmartDevice smartLight = new SmartLight(wifi);
        smartLight.control(); // Controlling Smart Light... Sending signal via WiFi to turn on/off

        SmartDevice smartThermostat = new SmartThermostat(zigbee);
        smartThermostat.control(); // Controlling Smart Thermostat... Sending signal via Zigbee to set temperature

        SmartDevice smartLock = new SmartLock(bluetooth);
        smartLock.control(); // Controlling Smart Lock... Sending signal via Bluetooth to lock/unlock
    }
}
