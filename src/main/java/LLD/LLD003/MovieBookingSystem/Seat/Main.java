package LLD.LLD003.MovieBookingSystem.Seat;

import LLD.LLD003.MovieBookingSystem.Booking.Booking;
import LLD.LLD003.MovieBookingSystem.Movie;
import LLD.LLD003.MovieBookingSystem.Show;
import LLD.LLD003.MovieBookingSystem.Theatre;
import LLD.LLD003.MovieBookingSystem.User;

import java.time.LocalDateTime;
import java.util.*;

public class Main {


    public static void main(String[] args) {


        MovieBookingService bookingSystem = MovieBookingService.getInstance();

        // Add movies
        Movie movie1 = new Movie("M1", "Movie 1", "Description 1", 120);
        Movie movie2 = new Movie("M2", "Movie 2", "Description 2", 135);
        bookingSystem.addMovie(movie1);
        bookingSystem.addMovie(movie2);

        // Add theaters
        Theatre theater1 = new Theatre("T1", "Theater 1", "Location 1", new ArrayList<>());
        Theatre theater2 = new Theatre("T2", "Theater 2", "Location 2", new ArrayList<>());
        bookingSystem.addTheatre(theater1);
        bookingSystem.addTheatre(theater2);

        // Add shows
        Show show1 = new Show("S1", movie1, theater1, LocalDateTime.now(), LocalDateTime.now().plusMinutes(movie1.getDurationInMinutes()), createSeats(10, 10));
        Show show2 = new Show("S2", movie2, theater2, LocalDateTime.now(), LocalDateTime.now().plusMinutes(movie2.getDurationInMinutes()), createSeats(8, 8));
        bookingSystem.addShow(show1);
        bookingSystem.addShow(show2);

        // Book tickets
        User user = new User("U1", "John Doe", "john@example.com");
        List<Seat> selectedSeats = Arrays.asList(show1.getSeats().get("1-5"), show1.getSeats().get("1-6"));
        Booking booking = bookingSystem.createBooking(user, show1, selectedSeats);
        if (booking != null) {
            System.out.println("Booking successful. Booking ID: " + booking.getId());
            bookingSystem.confirmBooking(booking.getId());
        } else {
            System.out.println("Booking failed. Seats not available.");
        }

        // Cancel booking
        bookingSystem.cancelBooking(booking.getId());
        System.out.println("Booking canceled. Booking ID: " + booking.getId());
    }

    private static Map<String, Seat> createSeats(int rows, int columns) {
        Map<String, Seat> seats = new HashMap<>();
        for (int row = 1; row <= rows; row++) {
            for (int col = 1; col <= columns; col++) {
                String seatId = row + "-" + col;
                SeatType seatType = (row <= 2) ? SeatType.PREMIUM : SeatType.NORMAL;
                double price = (seatType == SeatType.PREMIUM) ? 150.0 : 100.0;
                Seat seat = new Seat(seatId, row, col, SeatStatus.AVAILABLE, seatType, price);
                seats.put(seatId, seat);
            }
        }
        return seats;
    }

}

