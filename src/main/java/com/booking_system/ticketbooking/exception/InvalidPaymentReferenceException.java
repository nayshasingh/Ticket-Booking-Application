package com.booking_system.ticketbooking.exception;

public class InvalidPaymentReferenceException extends RuntimeException{
    public InvalidPaymentReferenceException(String message){
        super(message);
    }
}
