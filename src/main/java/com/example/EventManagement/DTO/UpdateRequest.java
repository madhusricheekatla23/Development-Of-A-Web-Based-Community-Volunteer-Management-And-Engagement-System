package com.example.EventManagement.DTO;

import lombok.Data;

@Data
public class UpdateRequest {
    private String phone;
    private String address;
    private String role;
}
