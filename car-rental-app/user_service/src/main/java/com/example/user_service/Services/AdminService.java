package com.example.user_service.Services;

import com.example.user_service.Models.Entities.User;
import com.example.user_service.Models.Repository.UserRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AdminService {
    @Autowired
    private Validator validator;

    private UserRepository userRepository;

    @Autowired
    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public void createAdmin(User user) {
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email already exists");
        }

        user.setUserRole(User.UserRole.ADMIN);

//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findUsers();
    }

}
