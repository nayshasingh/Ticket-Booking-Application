package com.booking_system.ticketbooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VenueAlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> handleVenueAlreadyExistsException(VenueAlreadyExistsException venueAlreadyExistsException){
        Map<String,String> body = new HashMap<>();
        body.put("message",venueAlreadyExistsException.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(VenueDoesNotExistException.class)
    public ResponseEntity<Map<String,String>> handleVenueDoesNotExistException(VenueDoesNotExistException venueDoesNotExistException){
        Map<String,String> body = new HashMap<>();
        body.put("message",venueDoesNotExistException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(SeatAlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> handleSeatAlreadyExistsException(SeatAlreadyExistsException seatAlreadyExistsException){
        Map<String,String> body = new HashMap<>();
        body.put("message",seatAlreadyExistsException.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(UserDoesNotExistException.class)
    public ResponseEntity<Map<String,String>> handleUserDoesNotExistException(UserDoesNotExistException userDoesNotExistException){
        Map<String,String> body = new HashMap<>();
        body.put("message",userDoesNotExistException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(SeatAlreadyBookedException.class)
    public ResponseEntity<Map<String,String>> handleUserDoesNotExistException(SeatAlreadyBookedException seatAlreadyBookedException){
        Map<String,String> body = new HashMap<>();
        body.put("message",seatAlreadyBookedException.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(SeatReleaseException.class)
    public ResponseEntity<Map<String,String>> handleSeatReleaseException(SeatReleaseException seatReleaseException){
        Map<String,String> body = new HashMap<>();
        body.put("message",seatReleaseException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(BookingCannotBeCancelledException.class)
    public ResponseEntity<Map<String,String>> handleBookingCannotBeCancelledException(BookingCannotBeCancelledException bookingCannotBeCancelledException){
        Map<String,String> body = new HashMap<>();
        body.put("message",bookingCannotBeCancelledException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(BookingDoesNotExistException.class)
    public ResponseEntity<Map<String,String>> handleBookingDoesNotExistException(BookingDoesNotExistException bookingDoesNotExistException){
        Map<String,String> body = new HashMap<>();
        body.put("message",bookingDoesNotExistException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
}
