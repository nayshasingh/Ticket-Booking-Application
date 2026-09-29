package com.booking_system.ticketbooking.exception;

public class VenueDoesNotExistException extends RuntimeException{
    public VenueDoesNotExistException(String message){
        super(message);
    }
}
