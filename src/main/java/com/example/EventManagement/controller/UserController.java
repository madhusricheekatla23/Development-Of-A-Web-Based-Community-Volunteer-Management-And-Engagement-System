package com.example.EventManagement.controller;

import com.example.EventManagement.DTO.*;
import com.example.EventManagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest req) {
        return service.register(req);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest req) {
        return service.login(req);
    }

    @PutMapping("/update")
    public String update(@RequestParam String emailId,
                         @RequestBody UpdateRequest req) {
        return service.update(emailId, req);
    }

    @PutMapping("/resetPassword")
    public String resetPassword(@RequestParam String emailId,
                                @RequestBody ResetPasswordRequest req) {
        return service.resetPassword(emailId, req);
    }

    @GetMapping("/profile")
    public ProfileResponse profile(@RequestParam String emailId) {
        return service.getProfile(emailId);
    }

    @PostMapping("/logout")
    public String logout(@RequestParam String emailId) {
        return service.logout(emailId);
    }
}
