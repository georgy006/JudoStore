package com.example.judoStore.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequest {
    private String name;
    private String email;
    private String password;
    private String city;
    private String phoneNumber;
}
