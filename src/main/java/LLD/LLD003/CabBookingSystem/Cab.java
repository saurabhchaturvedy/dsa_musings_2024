package LLD.LLD003.CabBookingSystem;

public class Cab {


    int id;
    String licenceNo;

    CabType type;

    Cab(int id, String licenceNo, CabType type) {

        this.id = id;
        this.licenceNo = licenceNo;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getLicenceNo() {
        return licenceNo;
    }

    public CabType getType() {
        return type;
    }
}
