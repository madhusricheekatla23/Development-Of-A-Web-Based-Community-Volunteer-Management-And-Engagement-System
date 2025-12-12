package com.example.EventManagement.DTO;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String EmailID;
    private String oldPassword;
    private String newPassword;
}
