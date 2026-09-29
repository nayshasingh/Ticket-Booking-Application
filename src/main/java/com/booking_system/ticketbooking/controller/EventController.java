package com.booking_system.ticketbooking.controller;

import com.booking_system.ticketbooking.dto.CreateEventRequest;
import com.booking_system.ticketbooking.entity.Event;
import com.booking_system.ticketbooking.service.EventService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public Event createEvent(@RequestBody @Valid CreateEventRequest createEventRequest){
        return eventService.createEvent(createEventRequest);
    }
}
