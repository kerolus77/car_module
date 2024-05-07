package com.example.car_module.car;
import com.example.car_module.User;
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

    public RentCarEntity() {
        super();
    }

    public RentCarEntity(String model, String licensePlate, String color, int year, String description, BrandEntity brand, Double rentPrice, Status status, User user) {
        super(model, licensePlate, color, year, description, brand, user);
        this.rentPrice = rentPrice;
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Double rentPrice) {
        this.rentPrice = rentPrice;
    }

    @Override
    public String toString() {
        return "CarEntity{" +
                "id=" + super.getId() +
                ", model='" + super.getModel() + '\'' +
                ", licensePlate='" + super.getLicensePlate() + '\'' +
                ", color='" + super.getColor() + '\'' +
                ", year=" + super.getYear() +
                ", description='" + super.getDescription() + '\'' +
                ", brand=" + super.getBrand() +
                ", status=" + status +
                ", rentPrice=" + rentPrice +
                '}';
    }
}
