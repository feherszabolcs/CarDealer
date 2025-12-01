package controllers;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Vehicle;
import utils.VehicleUtil.Category;

public class VehicleData extends AbstractTableModel {
    public List<Vehicle> vehicles = new ArrayList<>();
    public List<Vehicle> originalVehicles = new ArrayList<>(vehicles);

    /*
     * Method to read from a json file and store the information in a list of
     * objects (Vehicles).
     * The method will be called when the application starts.
     *
     * @param None
     * 
     * @throws Exception - If the json is corrupted, or the format doesn't match the
     * expected structure
     * 
     * @return None
     */
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
                        jsonObject.getInt("manufactureDate"),
                        jsonObject.getDouble("power"));
                vehicles.add(v);
                originalVehicles.add(v);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /*
     * Method to write into a json file the data stored in the list of Vehicles.
     * This method will be called every time we close the application.
     *
     * @param vehicles - list of Vehicles that need to be written into the json
     * file. Always contains all Vehicles from the table.
     * 
     * @throws Exception - If there are any problems with writing into the file.
     *
     * @return None
     */
    public void JSONWriter(List<Vehicle> vehicles) {
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

    /**
     * Overridden methods from the AbstractTableModel.
     * All of them used in the main table view since the this class is the one who
     * provides the data for it.
     */
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
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0 || columnIndex == 3 || columnIndex == 5)
            return Integer.class;
        if (columnIndex == 2)
            return Category.class;
        if (columnIndex == 6)
            return Double.class;
        return String.class;
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

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 3 || columnIndex == 4)
            return true;
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Vehicle v = vehicles.get(rowIndex);
        switch (columnIndex) {
            case 0:
                v.setId((Integer) aValue);
                break;
            case 1:
                v.setBrand((String) aValue);
                break;
            case 2:
                v.setCategory((Category) aValue);
                break;
            case 3:
                v.setPrice((Integer) aValue);
                break;
            case 4:
                v.setDescription((String) aValue);
                break;
            case 5:
                v.setManufactureDate((Integer) aValue);
                break;
            case 6:
                v.setPower((Double) aValue);
                break;
            default:
                break;
        }
    }

    /*
     * Method used to remove a Vehicle from the table. It also updates the view by
     * calling the fireTableDataChanged method.
     *
     * @param v - Vehicle that needs to be removed from the table.
     *
     * @return None
     */
    public void remove(Vehicle v) {
        vehicles.remove(v);
        originalVehicles.remove(v);
        fireTableDataChanged();
    }

    public void searchByBrand(String value) {
        if (!value.isBlank()) {
            vehicles = originalVehicles.stream()
                    .filter(r -> r.getBrand().toLowerCase().contains(value) || r.getBrand().startsWith(value))
                    .toList();
        } else
            vehicles = originalVehicles;
        fireTableDataChanged();
    }

    public void searchByCategory(String categoryName, String brandName) {
        if (categoryName.equals("ALL")) {
            vehicles = originalVehicles;
            return;
        }
        List<Vehicle> temp = new ArrayList<>();
        temp = originalVehicles.stream().filter(r -> r.getCategory().name().equals(categoryName)).toList();
        if (!brandName.isBlank())
            temp = temp.stream()
                    .filter(r -> r.getBrand().toLowerCase().contains(brandName) || r.getBrand().startsWith(brandName))
                    .toList();
        else
            temp = originalVehicles.stream().filter(r -> r.getCategory().name().equals(categoryName)).toList();
        vehicles = temp;
        fireTableDataChanged();
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }
}
