package com.example.EventManagement.DTO;

import lombok.Data;

@Data
public class LoginResponse {
    private String message;
    private String sessionId;
}
