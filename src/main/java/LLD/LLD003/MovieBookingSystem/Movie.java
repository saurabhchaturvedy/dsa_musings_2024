package LLD.LLD003.MovieBookingSystem;

public class Movie {


    String id;

    String name;

    String description;

    int durationInMinutes;


   public Movie(String id, String name, String description, int durationInMinutes) {


        this.id = id;
        this.name = name;
        this.description = description;
        this.durationInMinutes = durationInMinutes;
    }


    public int getDurationInMinutes() {

        return durationInMinutes;
    }
}
