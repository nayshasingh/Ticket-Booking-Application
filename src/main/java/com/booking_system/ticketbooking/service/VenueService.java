package com.booking_system.ticketbooking.service;

import com.booking_system.ticketbooking.entity.Venue;
import com.booking_system.ticketbooking.exception.VenueAlreadyExistsException;
import com.booking_system.ticketbooking.repository.VenueRepository;
import org.springframework.stereotype.Service;

@Service
public class VenueService {
    private final VenueRepository venueRepository;

    public VenueService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    public Venue createVenue(Venue venue){
        boolean exists = venueRepository.existsByName(venue.getName());
        if(exists){
            throw new VenueAlreadyExistsException("Venue name already exists");
        }
        return venueRepository.save(venue);
    }
}
