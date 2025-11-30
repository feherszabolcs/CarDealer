package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import controllers.VehicleData;
import model.Vehicle;

public class VehicleDataTest {
    VehicleData vData = new VehicleData();

    /**
     * Testing column count to render the correct number of columns in table.
     */
    @Test
    public void testColumnCount() {
        assertEquals(vData.getColumnCount(), 7);
    }

    /**
     * Testing vehicle removal from the list
     */
    @Test
    public void testRemoval() {
        vData.vehicles.add(new Vehicle(0, null, null, 0, null, 0, 0));
        vData.remove(new Vehicle(1, null, null, 0, null, 0, 0));
        assertEquals(vData.getVehicles().size(), 1);
    }

    /**
     * Testing searching by brand name
     * The changes must affect the vehicle list, but not the originalvehicles one
     */
    @Test
    public void testSearchByBrand() {
        List<Vehicle> result = new ArrayList<>();
        vData.originalVehicles.add(new Vehicle(0, "BMW", null, 0, null, 0, 0));
        vData.originalVehicles.add(new Vehicle(1, "BMW", null, 0, null, 0, 0));
        vData.originalVehicles.add(new Vehicle(2, "Audi", null, 0, null, 0, 0));
        result.add(new Vehicle(2, "Audi", null, 0, null, 0, 0));
        vData.searchByBrand("Audi");
        assertEquals(vData.vehicles.size(), result.size());
        assertEquals(vData.originalVehicles.size(), 3);
    }
}
