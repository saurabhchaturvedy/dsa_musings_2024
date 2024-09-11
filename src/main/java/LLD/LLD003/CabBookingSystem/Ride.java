package LLD.LLD003.CabBookingSystem;

public class Ride {


    int id;

    User user;
    Driver driver;

    Location source;
    Location destination;

    RideStatus status;


    Ride(int id, User user, Location source, Location destination) {

        this.id = id;
        this.user = user;
        this.source = source;
        this.destination = destination;
        this.status = RideStatus.REQUESTED;
    }


    public void startRide() {


        this.status = RideStatus.STARTED;
        System.out.println(" Ride has started for cab with driver as = " + this.driver.name);
    }

    public void endRide() {

        this.status = RideStatus.COMPLETED;
        System.out.println(" Ride has end for cab with driver as = " + this.driver.name);
    }


    public void setDriver(Driver driver) {

        this.status = RideStatus.ACCEPTED;
        this.driver = driver;
    }


    public Driver getDriver() {
        return driver;
    }

    public RideStatus getStatus() {

        return status;
    }

}
