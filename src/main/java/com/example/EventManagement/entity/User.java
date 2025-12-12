package com.example.EventManagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    private String emailId;

    private String password;
    private String phone;
    private String address;
    private String role;

    private String sessionId;
}
