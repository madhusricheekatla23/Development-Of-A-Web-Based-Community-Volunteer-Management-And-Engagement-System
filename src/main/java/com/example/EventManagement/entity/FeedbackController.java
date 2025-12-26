package com.example.EventManagement.controller;

import com.example.EventManagement.entity.Feedback;
import com.example.EventManagement.service.FeedbackService;
import com.example.EventManagement.service.SessionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;
    private final SessionService sessionService;

    public FeedbackController(FeedbackService feedbackService,
                              SessionService sessionService) {
        this.feedbackService = feedbackService;
        this.sessionService = sessionService;
    }

    @PostMapping("/submit")
    public Feedback submit(@RequestHeader("token") String token,
                           @RequestBody Feedback feedback) {
        sessionService.validateUser(token);
        return feedbackService.submit(feedback);
    }

    @GetMapping("/event/{eventId}")
    public List<Feedback> getByEvent(@PathVariable Integer eventId) {
        return feedbackService.getByEvent(eventId);
    }
}