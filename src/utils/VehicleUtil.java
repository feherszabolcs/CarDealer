package utils;

import java.util.List;

import model.Vehicle;

public class VehicleUtil {

    public enum Category {
        TRUCK,
        SEMITRUCK,
        MOTORBIKE,
        CAR
    }

    public static boolean isIdUnique(int id, List<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId() == id)
                return true;
        }
        return false;
    }

    public double kwToHp(double kw) {
        return kw * 1.34102209;
    }
}
