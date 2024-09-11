package LLD.LLD003.MovieBookingSystem.Seat;

public class Seat {


    String id;
    int row;
    int col;

    SeatStatus status;
    SeatType type;

    double price;

    public Seat(String id, int row, int col, SeatStatus status, SeatType type, double price) {
        this.id = id;
        this.row = row;
        this.col = col;
        this.status = status;
        this.type = type;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public SeatType getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }
}
