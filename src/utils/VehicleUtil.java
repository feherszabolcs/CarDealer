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

    /**
     * Checks whether the given ID already exists in the database.
     *
     * @param id      The ID that should be checked.
     * @param vehicles A list containing all existing IDs.
     * @return True if the ID does not exist yet and can be used as an identifier. False otherwise.
     */
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
