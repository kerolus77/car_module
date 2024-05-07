package com.example.transaction_service.controller;
import com.example.transaction_service.model.entity.Payment;
import com.example.transaction_service.model.entity.Transaction;
import com.example.transaction_service.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions/payments")
@CrossOrigin(origins = "*")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PutMapping("/update-payment-status/{paymentId}")
    public void updatePaymentStatus(@PathVariable Long paymentId, @RequestParam String status) {
        Payment.PaymentStatus paymentStatus;

        // Determine the PaymentStatus based on the status parameter
        switch (status) {
            case "PAID":
                paymentStatus = Payment.PaymentStatus.PAID;
                break;
            case "UNPAID":
                paymentStatus = Payment.PaymentStatus.UNPAID;
                break;
            case "HOLD":
                paymentStatus = Payment.PaymentStatus.HOLD;
                break;
            default:
                throw new IllegalArgumentException("Invalid payment status: " + status);
        }

        // Update the payment status
        paymentService.updatePaymentStatus(paymentId, paymentStatus);
    }


    @GetMapping("/getAllPayments") //list
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }


    @GetMapping("/getPaymentById/{paymentId}")
    public Payment getPaymentById(@PathVariable Long paymentId) {
        return paymentService.getPaymentById(paymentId);
    }


    @GetMapping("/getTotalPaymentsAmount") //total price of the payment
    public Double getTotalPaymentsAmount (){
        return paymentService.getTotalPaymentsAmount() ;
    }


    @GetMapping("/getTotalPaidPaymentsAmount")//total price of the Paid payment
    public Double getTotalPaidPaymentsAmount() {
        return paymentService.getTotalPaidPaymentsAmount();
    }


    @GetMapping("/getHoldedPayments") //list with hold payment
    public List<Payment> getHoldedPayments() {
        return paymentService.getHoldedPayments();
    }


    @GetMapping("/getPaidPayments") //list with paid payment+
    public List<Payment> getPaidPayments() {
        return paymentService.getPaidPayments();
    }


    @GetMapping("/getUnPaidPayments") //list with unpaid payment+
    public List<Payment> getUnPaidPayments() {
        return paymentService.getUnPaidPayments();
    }


    @GetMapping("/getPaymentWithTransaction/{paymentId}") // single    payment+transaction
    public Optional<Transaction> getPaymentWithTransaction(@PathVariable Long paymentId) {
        return paymentService.getTransactionByPaymentId(paymentId);
    }


    @GetMapping("/getPaymentsWithTransactions")//list   payment+transaction
    public List<Transaction> getPaymentsWithTransactions() {
        return paymentService.getTransactionsWithPayments();
    }

}
