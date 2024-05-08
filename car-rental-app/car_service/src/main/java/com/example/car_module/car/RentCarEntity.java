package com.example.car_module.car;
import com.example.car_module.brand.BrandEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Table(name = "rent_car")
@Entity
public class RentCarEntity  {
    @Id
    @SequenceGenerator(
            name = "RentCar_sequence",
            sequenceName = "RentCar_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "RentCar_sequence"
    )
    private Long id;
    @Size(max = 200000000) // Example: Limiting to 255 characters
    private String carImage;
    private String model;
    private String licensePlate;
    private int year;
    private String description;

    public enum Status {
        RENTED,
        AVAILABLE
    }
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.AVAILABLE;
    private Double rentPrice;
    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private BrandEntity brand;

    public RentCarEntity() {
    }

    public RentCarEntity(String licensePlate, BrandEntity brand, Double rentPrice, int year, String description, Status status, String model, String carImage) {
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.rentPrice = rentPrice;
        this.year = year;
        this.description = description;
        this.status = status;
        this.model = model;
        this.carImage = carImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCarImage() {
        return carImage;
    }

    public void setCarImage(String carImage) {
        this.carImage = carImage;
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public void setBrand(BrandEntity brand) {
        this.brand = brand;
    }

    public Double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public String getCarImage() {
        return carImage;
    }

    public void setCarImage(String carImage) {
        this.carImage = carImage;
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public void setBrand(BrandEntity brand) {
        this.brand = brand;
    }
}
