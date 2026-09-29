package com.booking_system.ticketbooking.exception;

public class EventSeatDoesNotExistException extends RuntimeException{
    public EventSeatDoesNotExistException(String message){
        super(message);
    }
}
