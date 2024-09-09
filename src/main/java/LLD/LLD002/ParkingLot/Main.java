package LLD.LLD002.ParkingLot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {


    public static void main(String[] args) throws InterruptedException {


        String nameOfParkingLot = "Sarjapur Parking Lot";
        Address address = new Address();
        address.setLocality("Sarjapur");
        address.setStreet("Carmelaram");
        address.setCity("Bengaluru");
        address.setState("Karnataka");
        address.setZipCode("560102");
        address.setCountry("India");
        Map<ParkingSlotType, Map<String, ParkingSlot>> allSlots = new HashMap<>();
        Map<String, ParkingSlot> compactSlot = new HashMap<>();
        compactSlot.put("C1", new ParkingSlot("C1", ParkingSlotType.COMPACT));
        compactSlot.put("C2", new ParkingSlot("C2", ParkingSlotType.COMPACT));
        compactSlot.put("C3", new ParkingSlot("C3", ParkingSlotType.COMPACT));
        allSlots.put(ParkingSlotType.COMPACT, compactSlot);

        Map<String, ParkingSlot> largeSlot = new HashMap<>();
        largeSlot.put("L1", new ParkingSlot("L1", ParkingSlotType.LARGE));
        largeSlot.put("L2", new ParkingSlot("L2", ParkingSlotType.LARGE));
        largeSlot.put("L3", new ParkingSlot("L3", ParkingSlotType.LARGE));
        allSlots.put(ParkingSlotType.LARGE, largeSlot);
        ParkingFloor parkingFloor = new ParkingFloor("1", allSlots);
        List<ParkingFloor> parkingFloors = new ArrayList<>();
        parkingFloors.add(parkingFloor);
        ParkingLot parkingLot = ParkingLot.getParkingLot(nameOfParkingLot, address, parkingFloors);

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleType(VehicleType.BIKE);
        vehicle.setRegistrationNumber("KA-01-MA-9999");

        Ticket ticket = parkingLot.generateTicket(vehicle);
        System.out.println(" ticket number >> " + ticket.getId());
        //persist the ticket to db here
        Thread.sleep(10000);
        double price = parkingLot.scanAndPay(ticket);
        System.out.println("price is >>" + price);
    }
}
