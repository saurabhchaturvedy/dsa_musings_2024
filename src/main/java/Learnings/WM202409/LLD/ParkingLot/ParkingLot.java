package Learnings.WM202409.LLD.ParkingLot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParkingLot {


    String name;


    Address address;

    List<ParkingFloor> parkingFloors;

    static ParkingLot parkingLot = null;


    ParkingLot(String name, Address address, List<ParkingFloor> parkingFloors) {

        this.name = name;
        this.address = address;
        this.parkingFloors = new ArrayList<>();

    }


    public static ParkingLot getParkingLot(String name, Address address, List<ParkingFloor> parkingFloors) {

        if (parkingLot == null) {

            parkingLot = new ParkingLot(name, address, parkingFloors);
        }

        return parkingLot;
    }


    public void addFloor(String name, Map<ParkingSlotType, Map<String, ParkingSlot>> parkingSlots) {

        ParkingFloor parkingFloor = new ParkingFloor(name, parkingSlots);

        parkingFloors.add(parkingFloor);
    }


    public void removeFloor(ParkingFloor parkingFloor) {

        parkingFloors.remove(parkingFloor);
    }


    public ParkingSlot getRelevantParkingSlot(Vehicle vehicle) {

        ParkingSlot parkingSlot = null;

        for (ParkingFloor parkingFloor : parkingFloors) {

            parkingSlot = parkingFloor.getParkingSlotForVehicle(vehicle);

            if (parkingSlot != null) {

                break;
            }
        }

        return parkingSlot;
    }


    public Ticket generateTicket(Vehicle vehicle) {

        ParkingSlot parkingSlot = getRelevantParkingSlot(vehicle);

        if (parkingSlot == null) {

            return null;

        } else {

            Ticket.getTicket(vehicle, parkingSlot);
        }

        return null;
    }


    public double scanAndPay(Ticket ticket) {

        long endTime = System.currentTimeMillis();
        ticket.getParkingSlot().removeVehicle(ticket.getVehicle());
        long duration = (System.currentTimeMillis() - endTime) / 1000;

        return ticket.getParkingSlot().getParkingSlotType().getPriceForParking(duration);
    }


}
