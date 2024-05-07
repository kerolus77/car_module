package com.example.transaction_service.AOP;

import com.example.transaction_service.dto.TransactionRequest;
import com.example.transaction_service.model.entity.Payment;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import java.util.Date;

@Aspect
@Component
public class RequestValidationAspect {

    @Before("execution(* com.example.transaction_service.controller.TransactionController.saveTransaction(..)) && args(transaction)")
    public void validateTransactionRequest(TransactionRequest transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction request cannot be null");
        }

        // Validate transactionType


        // Validate buyerId and carId
        if (transaction.getClientId() == null) {
            throw new IllegalArgumentException("Client ID is required");
        }
        if (transaction.getCarId() == null) {
            throw new IllegalArgumentException("Car ID is required");
        }

        // Validate paymentMethod
        Payment.PaymentMethod paymentMethod = transaction.getPaymentMethod();
        if (paymentMethod == null || (paymentMethod != Payment.PaymentMethod.CASH && paymentMethod != Payment.PaymentMethod.VISA)) {
            throw new IllegalArgumentException("Payment method must be CASH or VISA");
        }

        // Validate startDate and endDate for RentalTransaction


        // Validate totalPrice
        Double totalPrice = transaction.getTotalPrice();
        if (totalPrice == null || totalPrice <= 0 || totalPrice % 1 != 0) {
            throw new IllegalArgumentException("Total price is required and must be a positive integer");
        }
    }
}
