package com.example.transaction_service.controller;
import com.example.transaction_service.dto.TransactionRequest;
import com.example.transaction_service.model.FullTransactionResponse;
import com.example.transaction_service.model.entity.Transaction;
import com.example.transaction_service.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {
private TransactionService transactionService;
@Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/saveTransaction")
    public String saveTransaction(@RequestBody TransactionRequest transaction) {
        transactionService.newTransaction(transaction);
        return "saved Successfully";
    }


    @GetMapping("/getAllTransactions")
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/getAllTransactionsWithClients")
    public List<FullTransactionResponse> getAllTransactionsWithClients() {
        return transactionService.getAllTransactionsWithClients();
    }


    @GetMapping("/getAllTransactions/{clientId}")
    public List<Transaction> getAllTransactionsForClient(@PathVariable("clientId") Integer clientId) {
        return transactionService.getAllTransactionsForClient(clientId);
    }


    @GetMapping("/duration/{transactionId}")
    public Integer getRentalDuration(@PathVariable Long transactionId) {
        Transaction transaction = transactionService.getTransactionById(transactionId);
        if (transaction != null) {
            return transactionService.calculateRentalDuration(transaction);
        } else {
            return null; // or handle the case where the transaction with the given ID is not found
        }
    }

    @GetMapping("/remaining-time/{transactionId}")
    public Long getRemainingTime(@PathVariable Long transactionId) {
        Transaction transaction = transactionService.getTransactionById(transactionId);
        if (transaction != null) {
            return transactionService.calculateRemainingTime(transaction);
        } else {
            return null; // or handle the case where the transaction with the given ID is not found
        }
    }

}
