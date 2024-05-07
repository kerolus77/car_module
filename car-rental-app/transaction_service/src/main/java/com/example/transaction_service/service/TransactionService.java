package com.example.transaction_service.service;

import com.example.transaction_service.dto.TransactionRequest;
import com.example.transaction_service.model.entity.Transaction;
import com.example.transaction_service.model.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionService {
    private PaymentService paymentService;

    private TransactionRepository transactionRepository;
    @Autowired
    public TransactionService(PaymentService paymentService, TransactionRepository transactionRepository) {
        this.paymentService = paymentService;
        this.transactionRepository = transactionRepository;

    }
    public Transaction getTransactionById(Long transactionId) {
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    public void newTransaction(TransactionRequest transactionRequest) {
        // Convert TransactionRequest to Transaction entity
        Transaction transaction = new Transaction();
        transaction.setClientId(transactionRequest.getClientId());
        transaction.setCarId(transactionRequest.getCarId());
        transaction.setDateTime(transactionRequest.getDateTime());
        transaction.setStatus(Transaction.Status.UNPAID);
        transaction.setTotalPrice(transactionRequest.getTotalPrice());
        transaction.setStartDate(transactionRequest.getStartDate());
        transaction.setEndDate(transactionRequest.getEndDate());

        // Save the transaction entity using the repository
        transactionRepository.save(transaction);
        paymentService.createPayment(transactionRequest,transaction);

    }
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getAllTransactionsForClient(Integer clientId) {
        return transactionRepository.findAllByClientId(clientId);
    }

    public Integer calculateRentalDuration(Transaction transaction) {
        Date startDate = transaction.getStartDate();
        Date endDate = transaction.getEndDate();
        long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
        long diffInDays = diffInMillies / (1000 * 60 * 60 * 24);
        return (int) diffInDays;
    }
    public Long calculateRemainingTime(Transaction transaction) {
        Date currentDate = new Date();
        Date endDate = transaction.getEndDate();
        long diffInMillies = endDate.getTime() - currentDate.getTime();
        return diffInMillies / (1000 * 60 * 60 * 24);
    }
}

