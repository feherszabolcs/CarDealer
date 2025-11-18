package model;

public class VehicleUtil {

    enum Category {
        TRUCK,
        SEMITRUCK,
        MOTORBIKE,
        CAR
    }

    public double kwToHp(double kw) {
        return kw * 1.34102209;
    }
}
