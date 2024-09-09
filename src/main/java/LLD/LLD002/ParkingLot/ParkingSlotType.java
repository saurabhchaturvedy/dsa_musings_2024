package LLD.LLD002.ParkingLot;

public enum ParkingSlotType {


    SMALL {
        public double getPriceForParking(long duration) {
            return duration * 5.0;
        }
    }, COMPACT {
        public double getPriceForParking(long duration) {
            return duration * 10.0;
        }
    }, MEDIUM {
        public double getPriceForParking(long duration) {
            return duration * 15.0;
        }
    }, LARGE {
        public double getPriceForParking(long duration) {
            return duration * 20.0;
        }
    };

    public abstract double getPriceForParking(long duration);
}
