package com.example.transaction_service.model.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class Payment {
    @Id
    @SequenceGenerator(
            name="payment_sequence",
            sequenceName="payment_sequence",
            allocationSize=1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "payment_sequence"
    )
    private Long id;

    public enum PaymentStatus {
        PAID,
        UNPAID,
        HOLD
    }
    public enum PaymentMethod {
        VISA,
        CASH
    }

    @OneToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;
    private Date paymentDate ;
    private Double paymentAmount;
    @Enumerated(EnumType.STRING)
    @Column(name = "paymentStatus", nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.UNPAID;
    @Enumerated(EnumType.STRING)
    @Column(name = "paymentMethod", nullable = false)
    private PaymentMethod  paymentMethod;

    public Payment(Transaction transaction, Date paymentDate, PaymentStatus paymentStatus, PaymentMethod paymentMethod, Double paymentAmount) {
        this.transaction = transaction;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
        this.paymentMethod = paymentMethod;
        this.paymentAmount = paymentAmount;
    }

    public Payment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}