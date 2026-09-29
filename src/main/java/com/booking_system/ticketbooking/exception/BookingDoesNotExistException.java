package com.booking_system.ticketbooking.exception;

import org.springframework.data.jpa.repository.JpaRepository;

public class BookingDoesNotExistException extends RuntimeException {
    public BookingDoesNotExistException(String message){
        super(message);
    }
}
