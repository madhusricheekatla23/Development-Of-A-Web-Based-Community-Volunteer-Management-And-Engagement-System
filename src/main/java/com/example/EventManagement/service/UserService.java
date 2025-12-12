package com.example.EventManagement.service;

import com.example.EventManagement.DTO.*;
import com.example.EventManagement.entity.User;
import com.example.EventManagement.mapper.UserMapper;
import com.example.EventManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    // REGISTER
    public String register(RegisterRequest req) {

        if (repo.existsById(req.getEmailId())) {
            return "User already exists";
        }

        if (!req.getRole().equalsIgnoreCase("ORGANIZER") &&
                !req.getRole().equalsIgnoreCase("VOLUNTEER")) {
            return "Invalid role (Allowed: ORGANIZER, VOLUNTEER)";
        }

        User u = new User();
        u.setEmailId(req.getEmailId());
        u.setPassword(encoder.encode(req.getPassword()));
        u.setPhone(req.getPhone());
        u.setAddress(req.getAddress());
        u.setRole(req.getRole());
        u.setSessionId(null);

        repo.save(u);
        return "Registered Successfully";
    }

    // LOGIN
    public LoginResponse login(LoginRequest req) {

        User u = repo.findById(req.getEmailId()).orElse(null);
        LoginResponse res = new LoginResponse();

        if (u == null) {
            res.setMessage("User not found");
            return res;
        }

        if (!encoder.matches(req.getPassword(), u.getPassword())) {
            res.setMessage("Invalid password");
            return res;
        }

        String session = UUID.randomUUID().toString();
        u.setSessionId(session);
        repo.save(u);

        res.setMessage("Login Successful");
        res.setSessionId(session);
        return res;
    }

    // UPDATE PROFILE
    public String update(String emailId, UpdateRequest req) {

        User u = repo.findById(emailId).orElse(null);
        if (u == null) return "User not found";

        if (req.getRole() != null &&
                !req.getRole().equalsIgnoreCase("ORGANIZER") &&
                !req.getRole().equalsIgnoreCase("VOLUNTEER")) {
            return "Invalid role";
        }

        u.setPhone(req.getPhone());
        u.setAddress(req.getAddress());
        if (req.getRole() != null) u.setRole(req.getRole());

        repo.save(u);
        return "Updated Successfully";
    }

    // RESET PASSWORD
    public String resetPassword(String emailId, ResetPasswordRequest req) {

        User u = repo.findById(emailId).orElse(null);
        if (u == null) return "User not found";

        if (!encoder.matches(req.getOldPassword(), u.getPassword())) {
            return "Old password is incorrect";
        }

        u.setPassword(encoder.encode(req.getNewPassword()));
        repo.save(u);

        return "Password Updated Successfully";
    }

    // PROFILE
    public ProfileResponse getProfile(String emailId) {
        return UserMapper.toProfile(
                repo.findById(emailId).orElse(null)
        );
    }

    // LOGOUT
    public String logout(String emailId) {
        User u = repo.findById(emailId).orElse(null);
        if (u == null) return "User not found";

        u.setSessionId(null);
        repo.save(u);

        return "Logout Successful";
    }
}
