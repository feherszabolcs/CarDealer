package controllers;

import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Vehicle;
import model.VehicleUtil.Category;

public class VehicleData {
    public List<Vehicle> vehicles = new ArrayList<>();

    public void JSonReader() {
        try {
            String content = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get("lib/vehicles.json")));
            JSONArray jArray = new JSONArray(content);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jsonObject = jArray.getJSONObject(i).getJSONObject("vehicle");
                Vehicle v = new Vehicle(
                        jsonObject.getInt("id"),
                        jsonObject.getString("brand"),
                        Category.valueOf(jsonObject.getString("category")),
                        jsonObject.getInt("price"),
                        jsonObject.getString("description"),
                        LocalDate.parse(jsonObject.getString("manufactureDate")),
                        jsonObject.getDouble("power"));
                vehicles.add(v);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void JSONWriter(List<Vehicle> vehicles) {
        JSONArray jsonArray = new JSONArray();
        for (Vehicle vehicle : vehicles) {
            JSONObject jsonObject = new JSONObject();
            JSONObject objItem = new JSONObject();
            objItem.put("id", vehicle.getId());
            objItem.put("brand", vehicle.getBrand());
            objItem.put("category", vehicle.getCategory());
            objItem.put("price", vehicle.getPrice());
            objItem.put("description", vehicle.getDescription());
            objItem.put("manufactureDate", vehicle.getManufactureDate());
            objItem.put("power", vehicle.getPower());
            jsonObject.put("vehicle", objItem);
            jsonArray.put(jsonObject);
        }

        try (FileWriter file = new FileWriter("lib/vehicles.json")) {
            file.write(jsonArray.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
