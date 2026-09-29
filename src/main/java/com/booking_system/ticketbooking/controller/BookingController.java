package com.booking_system.ticketbooking.controller;

import com.booking_system.ticketbooking.dto.CreateBookingRequest;
import com.booking_system.ticketbooking.entity.Booking;
import com.booking_system.ticketbooking.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public Booking bookEvent(@RequestBody @Valid CreateBookingRequest createBookingRequest){
        return bookingService.bookEvent(createBookingRequest);
    }

    @PostMapping("/{bookingId}/cancel")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long bookingId){
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.noContent().build();
    }

}
