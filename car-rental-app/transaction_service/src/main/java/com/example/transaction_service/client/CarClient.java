package com.example.transaction_service.client;

import com.example.transaction_service.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "car-service", url = "http://localhost:8080/api/cars")
public interface CarClient {

        @GetMapping("/{id}")
        Car getCarById(@PathVariable("id") Long id);

}
