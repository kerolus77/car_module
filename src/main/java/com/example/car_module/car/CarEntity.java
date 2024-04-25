package com.example.car_module.car;

import com.example.car_module.brand.BrandEntity;
import jakarta.persistence.*;
import com.example.car_module.User;
@MappedSuperclass
public class CarEntity {
//    A Long id field that is annotated with @Id

    @SequenceGenerator(name = "car_sequence", sequenceName = "car_sequence", allocationSize = 1)
    @GeneratedValue(generator = "car_sequence")
    @Id
    private Long id;

    private String model;
    private String licensePlate;
    private String color;
    private int year;
    private String description;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private BrandEntity brand;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public CarEntity() {
    }

    public CarEntity(String model, String licensePlate, String color, int year, String description, BrandEntity brand, User user) {
        this.model = model;
        this.licensePlate = licensePlate;
        this.color = color;
        this.year = year;
        this.description = description;
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicensePlate() {
        return licensePlate;
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

    public BrandEntity getBrand() {
        return brand;
    }

    public void setBrand(BrandEntity brand) {
        this.brand = brand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
