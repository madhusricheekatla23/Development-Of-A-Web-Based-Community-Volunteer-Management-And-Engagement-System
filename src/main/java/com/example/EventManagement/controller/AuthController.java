package com.example.EventManagement.controller;

import com.example.EventManagement.entity.AuthToken;
import com.example.EventManagement.repository.AuthTokenRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthTokenRepository repo;

    public AuthController(AuthTokenRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/login")
    public AuthToken login(@RequestParam String email,
                           @RequestParam String role) {

        AuthToken token = new AuthToken();
        token.setEmail(email);
        token.setRole(role);
        token.setToken(UUID.randomUUID().toString());
        token.setCreatedAt(LocalDateTime.now());

        return repo.save(token);
    }
}
