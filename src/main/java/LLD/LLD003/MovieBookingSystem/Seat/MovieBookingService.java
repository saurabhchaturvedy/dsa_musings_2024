package LLD.LLD003.MovieBookingSystem.Seat;

import LLD.LLD003.MovieBookingSystem.Booking.Booking;
import LLD.LLD003.MovieBookingSystem.Booking.BookingStatus;
import LLD.LLD003.MovieBookingSystem.Movie;
import LLD.LLD003.MovieBookingSystem.Show;
import LLD.LLD003.MovieBookingSystem.Theatre;
import LLD.LLD003.MovieBookingSystem.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MovieBookingService {


    static MovieBookingService movieBookingService = null;

    List<Movie> movies;
    List<Theatre> theatre;

    Map<String, Show> shows;

    Map<String, Booking> bookings;


    public MovieBookingService() {
        this.movies = new ArrayList<>();
        this.theatre = new ArrayList<>();
        this.shows = new ConcurrentHashMap<>();
        this.bookings = new ConcurrentHashMap<>();
    }


    public static MovieBookingService getInstance() {

        if (movieBookingService == null) {

            movieBookingService = new MovieBookingService();
        }

        return movieBookingService;
    }


    public List<Movie> getMovies() {
        return movies;
    }

    public List<Theatre> getTheatre() {
        return theatre;
    }

    public Show getShow(String showId) {
        return shows.get(showId);
    }

    public Booking getBookings(String bookingId) {
        return bookings.get(bookingId);
    }


    public void addMovie(Movie movie) {
        this.movies.add(movie);
    }

    public void addTheatre(Theatre theatre) {

        this.theatre.add(theatre);
    }

    public void addShow(Show show) {

        shows.put(show.getId(), show);
    }


    public void addBooking(Booking booking) {

        bookings.put(booking.getId(), booking);
    }


    public Booking createBooking(User user, Show show, List<Seat> seatsRequested) {

        if (areSeatsAvailable(show, seatsRequested)) {

            markSeatsAsBooked(show, seatsRequested);
            double bookingCost = bookingCost(seatsRequested);
            String bookingId = UUID.randomUUID().toString();
            Booking booking = new Booking(bookingId, user, show, seatsRequested, bookingCost, BookingStatus.PENDING);

            return booking;
        }

        return null;
    }


    public boolean areSeatsAvailable(Show show, List<Seat> seatsRequested) {

        for (Seat seat : seatsRequested) {

            Seat currentSeat = show.getSeats().get(seat.getId());
            if (currentSeat != null && currentSeat.status == SeatStatus.BOOKED) {
                return false;
            }
        }

        return true;
    }


    public void markSeatsAsBooked(Show show, List<Seat> requestedSeats) {
        for (Seat seat : requestedSeats) {

            Seat currentSeat = show.getSeats().get(seat.getId());
            currentSeat.status = SeatStatus.BOOKED;
        }

    }


    public void markSeatsAsAvailable(Show show, List<Seat> requestedSeats) {
        for (Seat seat : requestedSeats) {

            Seat currentSeat = show.getSeats().get(seat.getId());
            currentSeat.status = SeatStatus.AVAILABLE;
        }

    }


    public double bookingCost(List<Seat> bookedSeats) {

        return bookedSeats.stream().mapToDouble(Seat::getPrice).sum();
    }


    public void confirmBooking(String bookingId) {

        Booking booking = bookings.get(bookingId);
        if (booking != null && booking.getStatus() == BookingStatus.PENDING) {
            booking.setStatus(BookingStatus.CONFIRMED);
        }
    }


    public void cancelBooking(String bookingId) {

        Booking booking = bookings.get(bookingId);
        if (booking != null && booking.getStatus() != BookingStatus.CANCELLED) {
            booking.setStatus(BookingStatus.CANCELLED);
            markSeatsAsAvailable(booking.getShow(), booking.getSeats());
        }
    }

}
