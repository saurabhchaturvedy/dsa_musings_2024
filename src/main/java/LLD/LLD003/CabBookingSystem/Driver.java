package LLD.LLD003.CabBookingSystem;

public class Driver {


    int id;

    String name;

    Cab cab;

    Location location;


    Driver(int id, String name, Location location,Cab cab) {

        this.id = id;
        this.name = name;
        this.cab = cab;
        this.location = location;
    }


    public boolean acceptRide(Ride ride) {

        if (ride != null) {

            ride.setDriver(this);
            System.out.println(" Driver " + this.name + " has accepted the ride");
            return true;
        }

        return false;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Cab getCab() {
        return cab;
    }

    public Location getLocation() {
        return location;
    }


    public void updateLocation(Location newLocation) {

        this.location = newLocation;
    }
}
