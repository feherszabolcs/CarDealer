package controllers;

import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Vehicle;
import utils.VehicleUtil.Category;

public class VehicleData extends AbstractTableModel {
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

    @Override
    public int getRowCount() {
        return vehicles.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Vehicle v = vehicles.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return v.getId();
            case 1:
                return v.getBrand();
            case 2:
                return v.getCategory();
            case 3:
                return v.getPrice();
            case 4:
                return v.getDescription();
            case 5:
                return v.getManufactureDate();
            default:
                return v.getPower();
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "Brand";
            case 2:
                return "Category";
            case 3:
                return "Price";
            case 4:
                return "Description";
            case 5:
                return "Manufacture date";
            default:
                return "Power (kW)";
        }

    }
}
