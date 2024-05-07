package com.example.user_service.Controllers;

import com.example.user_service.Models.Entities.User;
import com.example.user_service.Services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }

//    @Value("${jwt.secret}")
//    private String jwtSecret;
//
//    @Value("${jwt.expiration}")
//    private int jwtExpirationMs;

//    @Autowired
//    private HttpSession session;

    @GetMapping("/login")
    public String getLogin() {
        return "Login Page";
    }

    @GetMapping("/register")
    public String getRegister() {
        return "Register Page";
    }

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody Map<String, String> credentials, HttpServletRequest request) {
//        String email = credentials.get("email");
//        String password = credentials.get("password");
//        return userService.login(email, password, request);
//    }

    @PostMapping("/login")
    public User login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        return userService.login(email, password);
    }

//    private String generateJwtToken(Long userId) {
//        Date now = new Date();
//        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);
//
//        // Generate a secure secret key
//        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
//
//        return Jwts.builder()
//                .setSubject(Long.toString(userId))
//                .setIssuedAt(now)
//                .setExpiration(expiryDate)
//                .signWith(key)
//                .compact();
//    }


    @PostMapping("/register")
    public String register(@RequestBody User request) {
        try {
            userService.register(request);
            return "Registration successful";
        } catch (Exception e) {
            return "Registration failed: " + e.getMessage();
        }
    }

    @PutMapping("/updateProfile/{id}")
    public String updateProfile(@PathVariable Long id, @RequestBody User request) {
        try {
            userService.updateProfile(id, request);
            return "Profile updated successfully";
        } catch (Exception e) {
            return "Profile update failed: " + e.getMessage();
        }
    }


}

