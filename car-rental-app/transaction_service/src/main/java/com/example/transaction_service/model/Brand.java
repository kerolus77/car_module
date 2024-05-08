package com.example.transaction_service.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Brand {
    private Long id;
    private String name;
    private String country;
}
