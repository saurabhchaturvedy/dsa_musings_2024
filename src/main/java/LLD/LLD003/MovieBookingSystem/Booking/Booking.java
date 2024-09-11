package LLD.LLD003.MovieBookingSystem.Booking;

import LLD.LLD003.MovieBookingSystem.Seat.Seat;
import LLD.LLD003.MovieBookingSystem.Show;
import LLD.LLD003.MovieBookingSystem.User;

import java.util.List;

public class Booking {


    String id;

    User user;

    Show show;

    List<Seat> seats;


    double totalPrice;

    BookingStatus status;


    public Booking(String id, User user, Show show, List<Seat> seats, double totalPrice, BookingStatus status) {
        this.id = id;
        this.user = user;
        this.show = show;
        this.seats = seats;
        this.totalPrice = totalPrice;
        this.status = status;
    }


    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Show getShow() {
        return show;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }
}
