package com.example.car_module.car;
import com.example.car_module.brand.BrandEntity;
import jakarta.persistence.*;
@Table(name = "rent_car")
@Entity
public class RentCarEntity extends CarEntity {

    public enum Status {
        RENTED,
        AVAILABLE
    }
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.AVAILABLE;
    private Double rentPrice;

    public RentCarEntity(String image, String model, String licensePlate, int year, String description, BrandEntity brand, Status status, Double rentPrice) {
        super(image, model, licensePlate, year, description, brand);
        this.status = status;
        this.rentPrice = rentPrice;
    }

    public Double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
