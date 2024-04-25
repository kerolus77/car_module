package com.example.car_module.car;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import com.example.car_module.brand.brandEntity;
@Table(name = "sell_car")
@Entity
public class sellCarEntity extends carEntity{

    public enum Status {
        SOLD,
        AVAILABLE
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.AVAILABLE;
    private Double sellPrice;

    public sellCarEntity() {
        super();
    }

    public sellCarEntity(String model, String licensePlate, String color, int year, String description, brandEntity brand, Double sellPrice, Status status) {
        super(model, licensePlate, color, year, description, brand);
        this.sellPrice = sellPrice;
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
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
                ", sellPrice=" + sellPrice +
                '}';
    }

}
