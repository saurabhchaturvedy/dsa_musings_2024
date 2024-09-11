package LLD.LLD003.MovieBookingSystem;

import java.util.List;

public class Theatre {


    String id;

    String name;

    String location;

    List<Show> shows;


    public Theatre(String id, String name, String location, List<Show> shows) {

        this.id = id;
        this.name = name;
        this.location = location;
        this.shows = shows;
    }
}
