package com.example.user_service.Models.Entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Date;
@Entity
@Table(name = "users")
public  class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    private Long id;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp="01\\d{9}", message="Phone number must start with 01 and be 11 digits in total")
    @Column(unique = true)
    private String phone;

    @NotBlank(message = "Password is required")
    private String password;



    public enum UserRole {
        CLIENT,
        ADMIN
    }

    @NotNull(message = "User role is required")
    @Enumerated(EnumType.STRING)
    private UserRole userRole= UserRole.CLIENT;

    public User(UserRole userRole, String password, String phone, String email, String username) {
        this.userRole = userRole;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.username = username;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
