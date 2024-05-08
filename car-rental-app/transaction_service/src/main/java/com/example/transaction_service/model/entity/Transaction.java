package com.example.transaction_service.model.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class Transaction {
    @Id
    @SequenceGenerator(
            name = "transaction_sequence",
            sequenceName = "transaction_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transaction_sequence"
    )
    private Long id;

    public enum Status {
        PAID,
        UNPAID,
        HOLD
    }

    private Integer clientId;
    private Integer carId;
    private Date dateTime;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.UNPAID;
    private Double totalPrice;
    private String driverLicense;
    private Date startDate;
    private Date endDate;
    @Transient
    private Integer rentalDuration;

    public Transaction(Integer rentalDuration, Date endDate, Date startDate, String driverLicense, Status status, Double totalPrice, Date dateTime, Integer carId, Integer clientId) {
        this.rentalDuration = rentalDuration;
        this.endDate = endDate;
        this.startDate = startDate;
        this.driverLicense = driverLicense;
        this.status = status;
        this.totalPrice = totalPrice;
        this.dateTime = dateTime;
        this.carId = carId;
        this.clientId = clientId;
    }

    public Transaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(Integer rentalDuration) {
        this.rentalDuration = rentalDuration;
    }
}
