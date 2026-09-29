package com.booking_system.ticketbooking.exception;

public class VenueAlreadyExistsException extends RuntimeException{
    public VenueAlreadyExistsException(String message){
        super(message);
    }
}
