package com.example.transaction_service.client;

import com.example.transaction_service.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8080/api/users")
public interface UserClient {

    @GetMapping("/{id}")
    User getUserById(@PathVariable("id") Long id);
}
