package com.example.car_module.brand.dto;
import jakarta.validation.constraints.*;

public class BrandUpdateDTO {
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @Size(min = 2, max = 50, message = "Country must be between 2 and 50 characters")
    private String country;

    public BrandUpdateDTO() {
    }

    public BrandUpdateDTO(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }
}
