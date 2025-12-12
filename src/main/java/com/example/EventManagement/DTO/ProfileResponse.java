package com.example.EventManagement.DTO;

import lombok.Data;

@Data
public class ProfileResponse {
    private String emailId;
    private String phone;
    private String address;
    private String role;
    private String sessionId;
}
