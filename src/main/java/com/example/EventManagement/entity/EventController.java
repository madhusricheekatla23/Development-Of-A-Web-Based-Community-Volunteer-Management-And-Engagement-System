package com.example.EventManagement.controller;

import com.example.EventManagement.entity.Event;
import com.example.EventManagement.service.EventService;
import com.example.EventManagement.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final SessionService sessionService;

    @PostMapping("/create")
    public Event createEvent(@RequestHeader("token") String token,
                             @RequestBody Event event) {
        return eventService.create(event, sessionService.validateToken(token));
    }

    @PutMapping("/update/{id}")
    public Event updateEvent(@PathVariable Integer id,
                             @RequestHeader("token") String token,
                             @RequestBody Event event) {
        return eventService.update(id, event, sessionService.validateToken(token));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEvent(@PathVariable Integer id,
                            @RequestHeader("token") String token) {
        eventService.delete(id, sessionService.validateToken(token));
    }

    @GetMapping("/upcoming")
    public List<Event> upcomingEvents() {
        return eventService.upcomingEvents();
    }

    @GetMapping("/ongoing")
    public List<Event> ongoingEvents() {
        return eventService.ongoingEvents();
    }
}
