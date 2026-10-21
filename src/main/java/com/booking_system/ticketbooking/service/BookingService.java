package com.booking_system.ticketbooking.service;

import com.booking_system.ticketbooking.dto.BookingResponse;
import com.booking_system.ticketbooking.dto.CreateBookingRequest;
import com.booking_system.ticketbooking.entity.*;
import com.booking_system.ticketbooking.exception.*;
import com.booking_system.ticketbooking.repository.BookingRepository;
import com.booking_system.ticketbooking.repository.BookingSummaryProjection;
import com.booking_system.ticketbooking.repository.EventSeatRepository;
import com.booking_system.ticketbooking.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final EventSeatRepository eventSeatRepository;

    public BookingService(BookingRepository bookingRepository, UserRepository userRepository, EventSeatRepository eventSeatRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.eventSeatRepository = eventSeatRepository;
    }

    @Transactional
    public Booking bookEvent(CreateBookingRequest createBookingRequest){
        // check if user exists
        User user = userRepository.findById(createBookingRequest.getUserId()).orElseThrow(()-> new UserDoesNotExistException("User does not exist!"));

        // check if event seat exists
        EventSeat eventSeat = eventSeatRepository.findById(new EventSeatId(createBookingRequest.getEventId(), createBookingRequest.getSeatId())).orElseThrow(()->new EventSeatDoesNotExistException("Event Seat does not exist!"));

        // if eventseat is available then mark it as booked in atomic operation
        int bookedSeat = eventSeatRepository.bookSeat(createBookingRequest.getEventId(),createBookingRequest.getSeatId());
        if(bookedSeat == 0){
            throw new SeatAlreadyBookedException("Seat is already booked");
        }
        // java state was different from db state so explicitly add the status ( bulk update was done )
        eventSeat.setStatus(Status.BOOKED);
        // create booking object
        Booking booking = new Booking();
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setUser(user);
        booking.setCreatedAt(LocalDateTime.now());
        booking.setEventSeat(eventSeat);

        Booking savedBooking = bookingRepository.save(booking);
        System.out.println("ID before save: " + booking.getBookingId());
        System.out.println("ID after save: " + savedBooking.getBookingId());
        return savedBooking;
    }

    @Transactional
    public void cancelBooking(Long bookindId){
        // To cancel the booking
        // update the booking row from pending -> cancelled
        // then update the event seat from booked to available
        Booking booking = bookingRepository.findById(bookindId).orElseThrow(()->new BookingDoesNotExistException("Booking Does not Exist"));
        int cancelledRows = bookingRepository.cancelBooking(bookindId);
        if(cancelledRows == 0){
            throw new BookingCannotBeCancelledException("Booking Cannot Be Cancelled");
        }

        int releasedSeats = eventSeatRepository.releaseSeat(booking.getEventSeat().getEvent().getEventId(),booking.getEventSeat().getSeat().getSeatId());
        if(releasedSeats == 0){
            throw new SeatReleaseException("Seat cannot be released");
        }
    }
    private BookingResponse toBookingResponse(BookingSummaryProjection summary){
        return new BookingResponse(
                summary.getBookingId(),
                summary.getBookingStatus(),
                summary.getCreatedAt(),
                summary.getEventId(),
                summary.getSeatId(),
                summary.getEventName(),
                summary.getSeatNumber(),
                summary.getAmount()
        );
    }
    public Page<BookingResponse> fetchBookingByUser(Long userId, Pageable pageable){
        Page<BookingSummaryProjection> summaries = bookingRepository.findBookingSummariesByUserId(userId,pageable);
        return summaries.map(this::toBookingResponse);
    }

}
