package com.example.car_module.car;

import java.awt.*;

public class RentCarRequest {
    private TextArea carImage;
    private String model;
    private String licensePlate;
    private String color;
    private int year;
    private String description;
    private Long brandId; // Separate brandId field
    private Double rentPrice;
    private RentCarEntity.Status status;
    private Long userId;

    // Getters and setters
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public TextArea getCarImage() {
        return carImage;
    }

    public void setCarImage(TextArea carImage) {
        this.carImage = carImage;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public RentCarEntity.Status getStatus() {
        return status;
    }

    public void setStatus(RentCarEntity.Status status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
