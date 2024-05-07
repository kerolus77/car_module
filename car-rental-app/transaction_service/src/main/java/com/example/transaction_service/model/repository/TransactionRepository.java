package com.example.transaction_service.model.repository;

import com.example.transaction_service.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t WHERE t.clientId = :clientId")
    List<Transaction> findAllByClientId(Integer clientId);
    // You can add custom query methods here if needed
}

