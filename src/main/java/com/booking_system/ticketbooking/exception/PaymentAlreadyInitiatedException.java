package com.booking_system.ticketbooking.exception;

public class PaymentAlreadyInitiatedException extends RuntimeException{
    public PaymentAlreadyInitiatedException(String message){
        super(message);
    }
}
