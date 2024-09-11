package LLD.LLD003.MovieBookingSystem;

import LLD.LLD003.MovieBookingSystem.Seat.Seat;

import java.time.LocalDateTime;
import java.util.Map;

public class Show {


    String id;

    Movie movie;

    Theatre theatre;

    LocalDateTime startTime;
    LocalDateTime endTime;

    Map<String, Seat> seats;


    public Show(String id, Movie movie, Theatre theatre, LocalDateTime startTime, LocalDateTime endTime, Map<String, Seat> seats) {


        this.id = id;
        this.movie = movie;
        this.theatre = theatre;
        this.startTime = startTime;
        this.endTime = endTime;
        this.seats = seats;
    }


    public String getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Map<String, Seat> getSeats() {
        return seats;
    }
}
