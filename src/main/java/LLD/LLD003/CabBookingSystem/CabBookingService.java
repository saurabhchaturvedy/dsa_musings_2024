package LLD.LLD003.CabBookingSystem;

import java.util.ArrayList;
import java.util.List;

public class CabBookingService {


    List<Driver> availableDrivers;
    int rideCount = 0;


    CabBookingService() {

        this.rideCount = 0;
        this.availableDrivers = new ArrayList<>();
    }


    public void registerDriver(Driver driver) {

        this.availableDrivers.add(driver);
    }


    public Ride requestRide(User user, Location source, Location destination) {
        Driver nearestDriver = findNearestDriver(source);
        if (nearestDriver != null) {
            Ride ride = new Ride(++rideCount, user, source, destination);
            nearestDriver.acceptRide(ride);
            availableDrivers.remove(nearestDriver);
            return ride;
        }
        return null;
    }

    private Driver findNearestDriver(Location userLocation) {
        return availableDrivers.isEmpty() ? null : availableDrivers.get(0);
    }

    public void completeRide(Ride ride) {
        if (ride != null) {
            ride.endRide();
            availableDrivers.add(ride.getDriver());
        }
    }
}
