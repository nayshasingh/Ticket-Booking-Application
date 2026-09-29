package com.booking_system.ticketbooking.exception;

public class InvalidPaymentStateTransitionException extends RuntimeException{
    public InvalidPaymentStateTransitionException(String message){
        super(message);
    }
}
