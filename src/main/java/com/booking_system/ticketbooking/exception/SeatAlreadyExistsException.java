package com.booking_system.ticketbooking.exception;

public class SeatAlreadyExistsException extends RuntimeException{
    public SeatAlreadyExistsException(String message){
        super(message);
    }
}
