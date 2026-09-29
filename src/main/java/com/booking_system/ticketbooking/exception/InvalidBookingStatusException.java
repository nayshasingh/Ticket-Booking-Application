package com.booking_system.ticketbooking.exception;

public class InvalidBookingStatusException extends RuntimeException{
    public InvalidBookingStatusException(String message){
        super(message);
    }
}
