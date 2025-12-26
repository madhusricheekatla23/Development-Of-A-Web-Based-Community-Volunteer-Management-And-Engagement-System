package com.example.EventManagement.controller;

import com.example.EventManagement.entity.Registration;
import com.example.EventManagement.service.RegistrationService;
import com.example.EventManagement.service.SessionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {

    private final RegistrationService service;
    private final SessionService sessionService;

    public RegistrationController(RegistrationService service,
                                  SessionService sessionService) {
        this.service = service;
        this.sessionService = sessionService;
    }

    @PostMapping("/register")
    public Registration register(@RequestHeader("token") String token,
                                 @RequestParam Integer eventId) {
        String email = sessionService.validateUser(token).getEmail();
        return service.register(eventId, email);
    }

    @PostMapping("/unregister")
    public String unregister(@RequestHeader("token") String token,
                             @RequestParam Integer eventId) {
        String email = sessionService.validateUser(token).getEmail();
        service.unregister(eventId, email);
        return "Unregistered successfully";
    }

    @PostMapping("/check-in")
    public String checkIn(@RequestHeader("token") String token,
                          @RequestParam Integer eventId,
                          @RequestParam String email) {
        sessionService.validateVolunteer(token);
        service.checkIn(eventId, email);
        return "Checked-in successfully";
    }

    @GetMapping("/event/{eventId}")
    public List<Registration> getRegistrations(@RequestHeader("token") String token,
                                               @PathVariable Integer eventId) {
        sessionService.validateVolunteer(token);
        return service.getEventRegistrations(eventId);
    }
}