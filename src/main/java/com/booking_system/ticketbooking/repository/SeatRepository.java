package com.booking_system.ticketbooking.repository;

import com.booking_system.ticketbooking.entity.Seat;
import com.booking_system.ticketbooking.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat,Long> {
    public boolean existsByVenueAndSeatNumber(Venue venue, String seatNumber);
    List<Seat> findAllByVenue(Venue venue);
}
