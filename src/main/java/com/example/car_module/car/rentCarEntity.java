package com.example.car_module.car;
import com.example.car_module.brand.brandEntity;
import jakarta.persistence.*;
@Table(name = "rent_car")
@Entity
public class rentCarEntity extends carEntity{

    public enum Status {
        RENTED,
        AVAILABLE
    }
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.AVAILABLE;
    private Double rentPrice;

    public rentCarEntity() {
        super();
    }

    public rentCarEntity(String model, String licensePlate, String color, int year, String description, brandEntity brand, Double rentPrice, Status status) {
        super(model, licensePlate, color, year, description, brand);
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
        return "carEntity{" +
                "id=" + super.getId() +
                ", model='" + super.getModel() + '\'' +
                ", licensePlate='" + super.getLicensePlate() + '\'' +
                ", color='" + super.getColor() + '\'' +
                ", year=" + super.getYear() +
                ", description='" + super.getDescription() + '\'' +
                ", brand=" + super.getBrand().getName() +
                ", status=" + status +
                ", rentPrice=" + rentPrice +
                '}';
    }
}
