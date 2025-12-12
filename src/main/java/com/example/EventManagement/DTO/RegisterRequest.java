package com.example.EventManagement.DTO;

import lombok.Data;

@Data
public class RegisterRequest {
    private String emailId;
    private String password;
    private String phone;
    private String address;
    private String role;
}
