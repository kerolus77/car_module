package com.example.transaction_service.model;
import com.example.transaction_service.model.entity.Transaction;
import lombok.*;
import java.util.Date;

@Getter
@Setter

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

        public FullTransactionResponse(Long id, Long clientId, Long carId, Date dateTime, Transaction.Status status, Double totalPrice, String driverLicense, Date startDate, Date endDate, Integer rentalDuration, User user, Car car) {
                this.id = id;
                this.clientId = clientId;
                this.carId = carId;
                this.dateTime = dateTime;
                this.status = status;
                this.totalPrice = totalPrice;
                this.driverLicense = driverLicense;
                this.startDate = startDate;
                this.endDate = endDate;
                this.rentalDuration = rentalDuration;
                this.user = user;
                this.car = car;
        }
}
