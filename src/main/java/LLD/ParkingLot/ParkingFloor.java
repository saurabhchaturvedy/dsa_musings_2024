package LLD.ParkingLot;

import java.util.Map;

public class ParkingFloor {


    String name;

    Map<ParkingSlotType, Map<String, ParkingSlot>> parkingSlots;


    ParkingFloor(String name, Map<ParkingSlotType, Map<String, ParkingSlot>> parkingSlots) {

        this.name = name;
        this.parkingSlots = parkingSlots;

    }


    public ParkingSlot getParkingSlot(Vehicle vehicle) {

        VehicleType vehicleType = vehicle.getVehicleType();
        ParkingSlotType parkingSlotType = getParkingSlotType(vehicleType);
        Map<String, ParkingSlot> currentParkingSlots = parkingSlots.get(parkingSlotType);
        ParkingSlot availableParkingSlot = null;

        for (Map.Entry<String, ParkingSlot> parkingSlot : currentParkingSlots.entrySet()) {

            if (parkingSlot.getValue().isAvailable()) {

                availableParkingSlot = parkingSlot.getValue();
                parkingSlot.getValue().addVehicle(vehicle);
                break;
            }


        }


        return availableParkingSlot;
    }


    public ParkingSlotType getParkingSlotType(VehicleType vehicleType) {


        switch (vehicleType.name()) {

            case "SCOOTY":
                return ParkingSlotType.SMALL;
            case "BIKE":
                return ParkingSlotType.COMPACT;
            case "CAR":
                return ParkingSlotType.MEDIUM;
            case "PICKUP_TRUCK":
            case "MINI_BUS":
            case "TRUCK":
                return ParkingSlotType.LARGE;
        }

        return null;
    }
}
