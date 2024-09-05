package Learnings.WM202409.LLD.ParkingLot;



public class Ticket {


    String id;
    long startTime;

    long endTime;

    Vehicle vehicle;

    ParkingSlot parkingSlot;


    Ticket(Builder builder) {


        this.id = builder.id;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.vehicle = builder.vehicle;
        this.parkingSlot = builder.parkingSlot;
    }


    public static Ticket getTicket(Vehicle vehicle, ParkingSlot parkingSlot) {

        return new Builder(vehicle, parkingSlot).setStartTime(System.currentTimeMillis()).setId(vehicle.getRegistrationNumber() + System.currentTimeMillis()).build();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ParkingSlot getParkingSlot() {
        return parkingSlot;
    }

    public void setParkingSlot(ParkingSlot parkingSlot) {
        this.parkingSlot = parkingSlot;
    }

    private static class Builder {


        String id;
        long startTime;

        long endTime;

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

        public Builder setStartTime(long startTime) {

            this.startTime = startTime;
            return this;
        }


        public Builder setEndTime(long endTime) {

            this.endTime = endTime;
            return this;
        }


        public Ticket build() {

            return new Ticket(this);
        }
    }
}
