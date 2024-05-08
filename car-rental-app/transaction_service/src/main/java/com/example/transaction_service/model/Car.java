package com.example.transaction_service.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Car {
    private Long id;

    private String carImage;
    private String model;
    private String licensePlate;
    private int year;
    private String description;

    public enum Status {
        RENTED,
        AVAILABLE
    }
    private Status status;
    private Double rentPrice;
    private Brand brand;
}
