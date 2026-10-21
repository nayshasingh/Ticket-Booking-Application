package com.booking_system.ticketbooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.booking_system.ticketbooking.dto.ErrorResponse;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ErrorResponse buildErrorResponse(HttpStatus status,String message){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(status.getReasonPhrase());
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setMessage(message);
        errorResponse.setStatus(status.value());
        return  errorResponse;
    }

    @ExceptionHandler(VenueAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleVenueAlreadyExistsException(VenueAlreadyExistsException venueAlreadyExistsException){
        ErrorResponse errorResponse = buildErrorResponse(HttpStatus.CONFLICT,venueAlreadyExistsException.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(VenueDoesNotExistException.class)
    public ResponseEntity<ErrorResponse> handleVenueDoesNotExistException(VenueDoesNotExistException venueDoesNotExistException){
        ErrorResponse errorResponse = buildErrorResponse(HttpStatus.NOT_FOUND,venueDoesNotExistException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(SeatAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleSeatAlreadyExistsException(SeatAlreadyExistsException seatAlreadyExistsException){
        ErrorResponse errorResponse = buildErrorResponse(HttpStatus.CONFLICT,seatAlreadyExistsException.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(UserDoesNotExistException.class)
    public ResponseEntity<ErrorResponse> handleUserDoesNotExistException(UserDoesNotExistException userDoesNotExistException){
        ErrorResponse errorResponse = buildErrorResponse(HttpStatus.NOT_FOUND,userDoesNotExistException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(SeatAlreadyBookedException.class)
    public ResponseEntity<ErrorResponse> handleSeatAlreadyBookedException(SeatAlreadyBookedException seatAlreadyBookedException){
        ErrorResponse errorResponse = buildErrorResponse(HttpStatus.CONFLICT,seatAlreadyBookedException.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(SeatReleaseException.class)
    public ResponseEntity<ErrorResponse> handleSeatReleaseException(SeatReleaseException seatReleaseException){
        ErrorResponse errorResponse = buildErrorResponse(HttpStatus.NOT_FOUND,seatReleaseException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(BookingCannotBeCancelledException.class)
    public ResponseEntity<ErrorResponse> handleBookingCannotBeCancelledException(BookingCannotBeCancelledException bookingCannotBeCancelledException){
        ErrorResponse errorResponse = buildErrorResponse(HttpStatus.CONFLICT,bookingCannotBeCancelledException.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(BookingDoesNotExistException.class)
    public ResponseEntity<ErrorResponse> handleBookingDoesNotExistException(BookingDoesNotExistException bookingDoesNotExistException){
        ErrorResponse errorResponse = buildErrorResponse(HttpStatus.NOT_FOUND,bookingDoesNotExistException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        ErrorResponse errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST,"Validation Failed");
        Map<String,String> fieldErrors = new HashMap<>();
        List<FieldError> fieldErrorList= methodArgumentNotValidException.getBindingResult().getFieldErrors();
        for(FieldError fieldError:fieldErrorList){
            fieldErrors.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        errorResponse.setFieldErrors(fieldErrors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException illegalArgumentException){
        ErrorResponse errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, illegalArgumentException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
