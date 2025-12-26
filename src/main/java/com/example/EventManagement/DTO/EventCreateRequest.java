package com.example.EventManagement.DTO;

import java.time.LocalDate;

public class EventCreateRequest {
    public String name;
    public String description;
    public String address;
    public String city;
    public LocalDate startDate;
    public LocalDate endDate;
    public Integer maximumAllowedRegistrations;
    public Boolean registrationAllowed;
}
