package Learnings.WM202409.LLD.ParkingLot;

import java.util.Map;

public class ParkingFloor {


    String name;

    Map<ParkingSlotType, Map<String, ParkingSlot>> parkingSlots;


    ParkingFloor(String name, Map<ParkingSlotType, Map<String, ParkingSlot>> parkingSlots) {

        this.name = name;
        this.parkingSlots = parkingSlots;
    }


    public ParkingSlot getParkingSlotForVehicle(Vehicle vehicle) {

        VehicleType vehicleType = vehicle.getVehicleType();
        ParkingSlotType parkingSlotType = getSlotType(vehicleType);
        Map<String, ParkingSlot> parkingSlot = parkingSlots.get(parkingSlotType);

        ParkingSlot parkingS = null;

        for (Map.Entry<String, ParkingSlot> entry : parkingSlot.entrySet()) {

            if (entry.getValue().isAvailable) {

                parkingS = entry.getValue();
                parkingS.addVehicle(vehicle);
                break;
            }
        }

        return parkingS;
    }


    public ParkingSlotType getSlotType(VehicleType vehicleType) {


        switch (vehicleType.name()) {

            case "BIKE":
                return ParkingSlotType.SMALL;
            case "CAR":
                return ParkingSlotType.MEDIUM;
            case "PICKUP_TRUCK":
                return ParkingSlotType.COMPACT;
            case "BUS":
                return ParkingSlotType.LARGE;
        }

        return null;
    }
}
