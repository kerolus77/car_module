package com.example.transaction_service.model.repository;

import com.example.transaction_service.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query("SELECT SUM(p.paymentAmount) FROM Payment p WHERE p.paymentStatus = 'PAID'")
    Double getTotalPaidPaymentsAmount();

    @Query("SELECT p FROM Payment p WHERE p.paymentStatus = 'HOLD'")
    List<Payment> getHoldedPayments();
    @Query("SELECT p FROM Payment p WHERE p.paymentStatus = 'PAID'")
    List<Payment> getPaidPayments();
    @Query("SELECT p FROM Payment p WHERE p.paymentStatus = 'UNPAID'")
    List<Payment> getUnPaidPayments();
}
