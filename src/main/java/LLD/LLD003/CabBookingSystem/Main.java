package LLD.LLD003.CabBookingSystem;

public class Main {
    public static void main(String[] args) {
        // Create a cab booking service
        CabBookingService cabBookingService = new CabBookingService();

        // Register drivers and cabs
        Cab cab1 = new Cab(1, "ABC123", CabType.SEDAN);
        Driver driver1 = new Driver(1, "John Doe", new Location(12.9716, 77.5946), cab1);
        cabBookingService.registerDriver(driver1);

        // Register a user
        User user1 = new User(1, "Alice", new Location(12.9726, 77.5956));

        // User books a ride
        Ride ride1 = user1.bookRide(new Location(12.9300, 77.6200), cabBookingService);

        if (ride1 != null) {
            // Start and complete the ride
            ride1.startRide();
            cabBookingService.completeRide(ride1);
        }
    }
}
