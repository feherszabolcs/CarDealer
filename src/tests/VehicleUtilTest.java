package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import model.Vehicle;
import utils.VehicleUtil;

public class VehicleUtilTest {
    List<Vehicle> vehicleList;

    /**
     * Should return false, since the list is currently empty.
     */
    @Test
    public void testUniqueIdOnEmptyList() {
        vehicleList = new ArrayList<>();
        assertEquals(false, VehicleUtil.isIdUsed(2, vehicleList));
    }

    /**
     * Should return true, since there are no record with the same ID in the list.
     */
    @Test
    public void testIdUnused() {
        fillList();
        assertEquals(false, VehicleUtil.isIdUsed(3, vehicleList));
    }

    /**
     * Should return false, since there already exists a record with this ID in the
     * list.
     */
    @Test
    public void testIdUsed() {
        fillList();
        assertEquals(true, VehicleUtil.isIdUsed(1, vehicleList));
    }

    private void fillList() {
        vehicleList = new ArrayList<>();
        vehicleList.add(new Vehicle(1, "BMW", VehicleUtil.Category.CAR, 20000,
                "This car is awesome!", 2019, 100));
        vehicleList.add(new Vehicle(2, "Mercedes", VehicleUtil.Category.TRUCK, 10000,
                "This truck is awesome!", 2019, 534));
    }
}
