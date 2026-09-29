package com.booking_system.ticketbooking.controller;

import com.booking_system.ticketbooking.dto.CreateVenueRequest;
import com.booking_system.ticketbooking.entity.Venue;
import com.booking_system.ticketbooking.service.VenueService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/venues")
public class VenueController {
    private final VenueService venueService;

    public VenueController(VenueService venueService){
        this.venueService = venueService;
    }
    @PostMapping
    public Venue createVenue(@RequestBody @Valid CreateVenueRequest createVenueRequest){
        Venue venue = new Venue();
        venue.setName(createVenueRequest.getName());
        venue.setAddress(createVenueRequest.getAddress());
        return venueService.createVenue(venue);
    }
}
