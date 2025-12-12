package com.example.EventManagement.mapper;

import com.example.EventManagement.DTO.ProfileResponse;
import com.example.EventManagement.entity.User;

public class UserMapper {

    public static ProfileResponse toProfile(User u) {
        if (u == null) return null;

        ProfileResponse res = new ProfileResponse();
        res.setEmailId(u.getEmailId());
        res.setPhone(u.getPhone());
        res.setAddress(u.getAddress());
        res.setRole(u.getRole());
        res.setSessionId(u.getSessionId());
        return res;
    }
}
