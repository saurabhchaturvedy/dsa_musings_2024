package LLD.LLD003.CabBookingSystem;

import java.util.ArrayList;
import java.util.List;

public class User {


    int id;

    String name;

    Location location;

    List<Ride> rideHistory;


    User(int id, String name, Location location) {

        this.id = id;
        this.name = name;
        this.location = location;
        this.rideHistory = new ArrayList<>();
    }


    public Ride bookRide(Location destination, CabBookingService cabBookingService) {
        Ride ride = cabBookingService.requestRide(this, location, destination);
        if (ride != null) {
            rideHistory.add(ride);
            System.out.println("Ride booked successfully for " + name);
        } else {
            System.out.println("No cabs available at the moment.");
        }
        return ride;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public List<Ride> getRideHistory() {
        return rideHistory;
    }
}
