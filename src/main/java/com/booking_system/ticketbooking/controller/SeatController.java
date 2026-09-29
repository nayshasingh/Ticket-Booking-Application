package com.booking_system.ticketbooking.controller;

import com.booking_system.ticketbooking.dto.CreateSeatRequest;
import com.booking_system.ticketbooking.entity.Seat;
import com.booking_system.ticketbooking.service.SeatService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seats")
public class SeatController {
    private final SeatService service;

    public SeatController(SeatService service) {
        this.service = service;
    }

    @PostMapping
    public Seat createSeat(@RequestBody @Valid CreateSeatRequest createSeatRequest){
        return service.createSeat(createSeatRequest);
    }
}
