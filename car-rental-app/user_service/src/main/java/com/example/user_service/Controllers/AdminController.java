package com.example.user_service.Controllers;

import com.example.user_service.Models.Entities.User;
import com.example.user_service.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class AdminController {
    private final com.example.user_service.Services.AdminService AdminService;

    @Autowired
    public AdminController(AdminService adminService) {

        this.AdminService = adminService;
    }

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return AdminService.getUsers();
    }

    @PostMapping("/activateUser")
    public ResponseEntity<String> activateUser(@RequestParam Long userId) {
        try {
            AdminService.activateUser(userId);
            return ResponseEntity.ok("User activated successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to activate user: " + e.getMessage());
        }
    }
    @PostMapping("/deactivateUser")
    public ResponseEntity<String> deactivateUser(@RequestParam Long userId) {
        try {
            AdminService.deactivateUser(userId);
            return ResponseEntity.ok("User deactivated successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to activate user: " + e.getMessage());
        }
    }

    @PostMapping("/createAdmin")
    public String createAdmin(@RequestBody User request) {
        try {
            AdminService.createAdmin(request);
            return "Admin created successfully";
        } catch (Exception e) {
            return "Admin creation failed: " + e.getMessage();
        }
    }

}
