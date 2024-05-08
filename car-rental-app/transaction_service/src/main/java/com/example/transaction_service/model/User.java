package com.example.transaction_service.model;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private Long id;
    private String username;
    private String email;
    private String phone;
    public enum UserRole {
        CLIENT,
        ADMIN
    }
    private UserRole userRole;
}
