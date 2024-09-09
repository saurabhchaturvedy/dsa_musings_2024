package LLD.LLD002.ParkingLot;

public class Ticket {


    String id;
    long entryTime;
    long exitTime;

    Vehicle vehicle;

    ParkingSlot parkingSlot;


    public static Ticket getTicket(Vehicle vehicle, ParkingSlot parkingSlot) {

        String id = vehicle.getRegistrationNumber() + System.currentTimeMillis();
        System.out.println(" Ticket ID : " + id);
        return new Builder(vehicle, parkingSlot).setEntryTime(System.currentTimeMillis()).setId(id).build();
    }


    Ticket(Builder builder) {

        this.id = builder.id;
        this.entryTime = builder.entryTime;
        this.exitTime = builder.exitTime;
        this.vehicle = builder.vehicle;
        this.parkingSlot = builder.parkingSlot;
    }


    public String getId() {
        return id;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public long getExitTime() {
        return exitTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSlot getParkingSlot() {
        return parkingSlot;
    }

    public static class Builder {


        String id;
        long entryTime;
        long exitTime;

        Vehicle vehicle;

        ParkingSlot parkingSlot;


        Builder(Vehicle vehicle, ParkingSlot parkingSlot) {

            this.vehicle = vehicle;
            this.parkingSlot = parkingSlot;
        }


        public Builder setId(String id) {

            this.id = id;
            return this;
        }


        public Builder setEntryTime(long entryTime) {

            this.entryTime = entryTime;
            return this;
        }


        public Builder setExitTime(long exitTime) {

            this.exitTime = exitTime;
            return this;
        }

        public Ticket build() {

            return new Ticket(this);
        }
    }
}
