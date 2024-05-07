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

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotNull(message = "Date of birth is required")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;


    public enum UserRole {
        CLIENT,
        ADMIN
    }

    @NotNull(message = "User role is required")
    @Enumerated(EnumType.STRING)
    private UserRole userRole= UserRole.CLIENT;

    private Boolean locked ;

    private String personalPhoto;

    private String idPhotoFront;

    private String idPhotoBack;

    public User() {
    }

    public User(String username, String email, String phone, String password, String gender, Date dateOfBirth, UserRole userRole, Boolean locked, String personalPhoto, String idPhotoFront, String idPhotoBack) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.userRole = userRole;
        this.locked = locked;
        this.personalPhoto = personalPhoto;
        this.idPhotoFront = idPhotoFront;
        this.idPhotoBack = idPhotoBack;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public String getPersonalPhoto() {
        return personalPhoto;
    }

    public void setPersonalPhoto(String personalPhoto) {
        this.personalPhoto = personalPhoto;
    }

    public String getIdPhotoFront() {
        return idPhotoFront;
    }

    public void setIdPhotoFront(String idPhotoFront) {
        this.idPhotoFront = idPhotoFront;
    }

    public String getIdPhotoBack() {
        return idPhotoBack;
    }

    public void setIdPhotoBack(String idPhotoBack) {
        this.idPhotoBack = idPhotoBack;
    }
}
