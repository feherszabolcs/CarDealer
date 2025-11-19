package model;

import java.time.LocalDate;

import model.VehicleUtil.Category;

public class Vehicle {
    private int id;
    private String brand;
    private Category category;
    private int price;
    private String description;
    private LocalDate manufactureDate;
    private double power;

    public Vehicle(int id, String brand, Category category, int price, String description,
            LocalDate manufactureDate, double power) {
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

    public LocalDate getManufactureDate() {
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
}
