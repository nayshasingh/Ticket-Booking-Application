package com.booking_system.ticketbooking.service;

import com.booking_system.ticketbooking.dto.CreateSeatRequest;
import com.booking_system.ticketbooking.entity.Seat;
import com.booking_system.ticketbooking.entity.Venue;
import com.booking_system.ticketbooking.exception.SeatAlreadyExistsException;
import com.booking_system.ticketbooking.exception.VenueDoesNotExistException;
import com.booking_system.ticketbooking.repository.SeatRepository;
import com.booking_system.ticketbooking.repository.VenueRepository;
import org.springframework.stereotype.Service;

@Service
public class SeatService {
    private final SeatRepository seatRepository;
    private final VenueRepository venueRepository;

    public SeatService(SeatRepository seatRepository, VenueRepository venueRepository) {
        this.seatRepository = seatRepository;
        this.venueRepository = venueRepository;
    }

    public Seat createSeat(CreateSeatRequest createSeatRequest){
        Long venueId = createSeatRequest.getVenueId();
        // venue must exist
        Venue venue = venueRepository.findById(venueId).orElseThrow(()->new VenueDoesNotExistException("Venue does not exist!"));

        // unique constraint check - venue + seat
        boolean seatExists = seatRepository.existsByVenueAndSeatNumber(venue,createSeatRequest.getSeatNumber());
        if(seatExists){
            throw new SeatAlreadyExistsException("Seat already exists at given Venue");
        }
        Seat seat = new Seat();
        seat.setSeatNumber(createSeatRequest.getSeatNumber());
        seat.setVenue(venue);
        return seatRepository.save(seat);
    }
}
