package com.example.transaction_service.model;
import lombok.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FullTransactionResponse {
        private Long id;
        public enum Status {
            PAID,
            UNPAID,
            HOLD
        }
        private Long clientId;
        private Long carId;
        private Date dateTime;
        private com.example.transaction_service.model.entity.Transaction.Status status;
        private Double totalPrice;
        private String driverLicense;
        private Date startDate;
        private Date endDate;
        private Integer rentalDuration;
        private User user;
        private Car car;
}
