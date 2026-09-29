package com.booking_system.ticketbooking.exception;

public class PaymentDoesNotExistException extends RuntimeException{
    public PaymentDoesNotExistException(String message){
        super(message);
    }
}
