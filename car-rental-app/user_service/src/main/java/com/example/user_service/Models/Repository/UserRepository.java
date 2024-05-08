package com.example.user_service.Models.Repository;

import com.example.user_service.Models.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.userRole = 'CLIENT'")
    List<User> findUsers();

    @Query("SELECT u FROM User u WHERE u.phone = :phone")
    User findByPhone(@Param("phone") String phone);
}
