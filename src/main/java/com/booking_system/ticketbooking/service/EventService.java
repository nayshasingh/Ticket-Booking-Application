package com.booking_system.ticketbooking.service;

import com.booking_system.ticketbooking.dto.CreateEventRequest;
import com.booking_system.ticketbooking.entity.*;
import com.booking_system.ticketbooking.exception.VenueDoesNotExistException;
import com.booking_system.ticketbooking.repository.EventRepository;
import com.booking_system.ticketbooking.repository.EventSeatRepository;
import com.booking_system.ticketbooking.repository.SeatRepository;
import com.booking_system.ticketbooking.repository.VenueRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final VenueRepository venueRepository;
    private final EventRepository eventRepository;
    private final SeatRepository seatRepository;
    private final EventSeatRepository eventSeatRepository;

    public EventService(VenueRepository venueRepository, EventRepository eventRepository, SeatRepository seatRepository, EventSeatRepository eventSeatRepository){
        this.venueRepository = venueRepository;
        this.eventRepository = eventRepository;
        this.seatRepository = seatRepository;
        this.eventSeatRepository = eventSeatRepository;
    }
    @Transactional
    public Event createEvent(CreateEventRequest createEventRequest){
        Long venueId = createEventRequest.getVenueId();
        // does venue exist in db
        Venue venue = venueRepository.findById(venueId).orElseThrow(()->new VenueDoesNotExistException("Venue does not exist!"));
        Event event = new Event();
        event.setName(createEventRequest.getName());
        event.setVenue(venue);
        event.setStartTime(createEventRequest.getStartTime());
        event.setDurationMinutes(createEventRequest.getDurationMinutes());

        Event savedEvent = eventRepository.save(event);

        List<Seat> seats = seatRepository.findAllByVenue(venue);
        List<EventSeat>eventSeats = new ArrayList<>();
        for(Seat seat:seats){
            EventSeat eventSeat = new EventSeat();
            EventSeatId eventSeatId = new EventSeatId(event.getEventId(), seat.getSeatId());
            eventSeat.setId(eventSeatId);
            eventSeat.setEvent(event);
            eventSeat.setSeat(seat);
            eventSeat.setStatus(Status.AVAILABLE);
            eventSeat.setPrice(createEventRequest.getPrice());
            eventSeats.add(eventSeat);
        }

        eventSeatRepository.saveAll(eventSeats);
        return savedEvent;
    }
}
