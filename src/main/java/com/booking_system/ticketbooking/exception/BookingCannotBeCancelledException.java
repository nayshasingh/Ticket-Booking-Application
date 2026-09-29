package com.booking_system.ticketbooking.exception;

public class BookingCannotBeCancelledException extends RuntimeException{
    public BookingCannotBeCancelledException(String message){
        super(message);
    }
}
