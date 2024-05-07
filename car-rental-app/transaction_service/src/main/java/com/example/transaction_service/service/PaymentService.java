package com.example.transaction_service.service;
import com.example.transaction_service.dto.TransactionRequest;
import com.example.transaction_service.model.entity.Payment;
import com.example.transaction_service.model.entity.Transaction;
import com.example.transaction_service.model.repository.PaymentRepository;
import com.example.transaction_service.model.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;
    private TransactionRepository transactionRepository;
    @Autowired
    public PaymentService(PaymentRepository paymentRepository, TransactionRepository transactionRepository) {
        this.paymentRepository = paymentRepository;
        this.transactionRepository = transactionRepository;
    }

    public void createPayment(TransactionRequest transactionRequest, Transaction transaction){
        Payment payment = new Payment();
        payment.setPaymentAmount(transactionRequest.getTotalPrice());
        LocalDate localDate  = LocalDate.now();
        payment.setPaymentDate(java.sql.Date.valueOf(localDate));
        payment.setPaymentMethod(transactionRequest.getPaymentMethod());
        payment.setTransaction(transaction);
        paymentRepository.save(payment);
    }

    private Transaction.Status convertPaymentStatusToTransactionStatus(Payment.PaymentStatus paymentStatus) {
        switch (paymentStatus) {
            case PAID:
                return Transaction.Status.PAID;
            case UNPAID:
                return Transaction.Status.UNPAID;
            case HOLD:
                return Transaction.Status.HOLD;
            default:
                throw new IllegalArgumentException("Invalid payment status: " + paymentStatus);
        }
    }

    public void updatePaymentStatus(Long paymentId, Payment.PaymentStatus newStatus) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + paymentId));

        payment.setPaymentStatus(newStatus);

        // Convert Payment.PaymentStatus to Transaction.Status
        Transaction.Status transactionStatus = convertPaymentStatusToTransactionStatus(newStatus);

        // Call the transaction service to change the status
        Transaction transaction = transactionRepository.findById(payment.getTransaction().getId())
                .orElseThrow(() -> new RuntimeException("transaction not found with id: " + payment.getTransaction().getId()));

        transaction.setStatus(convertPaymentStatusToTransactionStatus(newStatus));

        transactionRepository.save(transaction);
        paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Double getTotalPaymentsAmount() {
        List <Payment> allPayments = paymentRepository.findAll();
        double totalAmount = 0.0;
        for (Payment payment : allPayments) {
            totalAmount += payment.getPaymentAmount();
        }
        return totalAmount;
    }

    public Payment getPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + paymentId));
    }

    public Double getTotalPaidPaymentsAmount() {
        return paymentRepository.getTotalPaidPaymentsAmount();
    }

    public List<Payment> getHoldedPayments() {
        return paymentRepository.getHoldedPayments();
    }

    public List<Payment> getPaidPayments() {
        return paymentRepository.getPaidPayments();
    }

    public List<Payment> getUnPaidPayments() {
        return paymentRepository.getUnPaidPayments();
    }

    public Optional<Transaction> getTransactionByPaymentId(Long paymentId) {
        return paymentRepository.findById(paymentId)
                .map(Payment::getTransaction);
    }

    public List<Transaction> getTransactionsWithPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(Payment::getTransaction)
                .collect(Collectors.toList());
    }

}
