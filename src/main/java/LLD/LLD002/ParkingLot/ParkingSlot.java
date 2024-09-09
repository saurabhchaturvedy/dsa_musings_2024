package LLD.LLD002.ParkingLot;

public class ParkingSlot {


    String name;
    boolean isAvailable=true;

    Vehicle vehicle;
    ParkingSlotType parkingSlotType;


    ParkingSlot(String name, ParkingSlotType parkingSlotType) {

        this.vehicle = vehicle;
        this.parkingSlotType = parkingSlotType;
    }


    public void addVehicle(Vehicle vehicle) {

        this.vehicle = vehicle;
        this.isAvailable = false;
    }


    public void removeVehicle(Vehicle vehicle) {

        this.vehicle = null;
        this.isAvailable = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ParkingSlotType getParkingSlotType() {
        return parkingSlotType;
    }

    public void setParkingSlotType(ParkingSlotType parkingSlotType) {
        this.parkingSlotType = parkingSlotType;
    }
}
