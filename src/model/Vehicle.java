package model;

import utils.VehicleUtil.Category;

public class Vehicle {
    private int id;
    private String brand;
    private Category category;
    private int price;
    private String description;
    private int manufactureDate;
    private double power;

    public Vehicle(int id, String brand, Category category, int price, String description,
            int manufactureDate, double power) {
        this.brand = brand;
        this.id = id;
        this.category = category;
        this.description = description;
        this.price = price;
        this.power = power;
        this.manufactureDate = manufactureDate;
    }

    // GETTERS
    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public int getManufactureDate() {
        return manufactureDate;
    }

    public double getPower() {
        return power;
    }

    public int getPrice() {
        return price;
    }

    // SETTERS
    public void setPrice(int price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setManufactureDate(int manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public void setPower(double power) {
        this.power = power;
    }
}
