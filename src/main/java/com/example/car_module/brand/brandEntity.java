package com.example.car_module.brand;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "brand")
public class brandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "Name is mandatory")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotNull(message = "Country is mandatory")
    @Size(min = 2, max = 50, message = "Country must be between 2 and 50 characters")
    private String country;

    public brandEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public brandEntity(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setName(String name) {
        this.name = name;
    }
}
