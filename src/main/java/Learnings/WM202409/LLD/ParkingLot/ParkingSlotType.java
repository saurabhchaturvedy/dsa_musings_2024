package Learnings.WM202409.LLD.ParkingLot;

public enum ParkingSlotType {


    COMPACT {
        public double getPriceForParking(long duration) {
            return 0.5 * duration;
        }
    }, SMALL {
        public double getPriceForParking(long duration) {
            return 1 * duration;
        }
    }, MEDIUM {
        public double getPriceForParking(long duration) {
            return 1.5 * duration;
        }
    }, LARGE {
        public double getPriceForParking(long duration) {
            return 2 * duration;
        }
    };


    public abstract double getPriceForParking(long duration);
}
