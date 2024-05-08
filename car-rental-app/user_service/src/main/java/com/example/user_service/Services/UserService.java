package com.example.user_service.Services;

import com.example.user_service.Models.Entities.User;
import com.example.user_service.Models.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.validation.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;


import java.util.Objects;
import java.util.Set;



@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validator validator;


//    private final PasswordEncoder passwordEncoder;

    @Autowired
//    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
    }



//    public boolean isValidUser(String email, String password) {
//        User user = userRepository.findByEmail(email);
//        return user != null && passwordEncoder.matches(password, user.getPassword());
//    }

//    public ResponseEntity<String> login(String email, String password, HttpServletRequest request) {
//        User user = userRepository.findByEmail(email);
//        if (user != null && Objects.equals(user.getPassword(), password)) {
//            // Store user ID in session
//            request.getSession().setAttribute("userId", user.getId());
//            return ResponseEntity.ok("Login successful");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//        }
//    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && Objects.equals(user.getPassword(), password)) {
            return user; // Return user ID if login successful
        } else {
            return null; // Return null if login failed
        }
    }


    public void register(User user) {
        // Perform any necessary validation here
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email already exists");
        }
        if (userRepository.findByPhone(user.getPhone()) != null) {
            throw new RuntimeException("phone already exists");
        }

        // If validation passes, save the user
        user.setUserRole(User.UserRole.CLIENT);
        userRepository.save(user);
    }

    public void updateProfile(Long id , User user) {
        User retUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        if(user.getUsername() != null && !user.getUsername().isEmpty()) {
            retUser.setUsername(user.getUsername());
        }
        if(user.getPhone() != null && !user.getPhone().isEmpty()) {
            retUser.setPhone(user.getPhone());
        }

        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            retUser.setPassword(user.getPassword());
        }





//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(retUser);
    }
}
