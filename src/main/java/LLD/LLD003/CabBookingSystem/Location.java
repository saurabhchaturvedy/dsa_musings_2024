package LLD.LLD003.CabBookingSystem;

public class Location {


    double latitude;
    double longitude;

    Location(double latitude, double longitude) {

        this.latitude = latitude;
        this.longitude = longitude;
    }


    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
