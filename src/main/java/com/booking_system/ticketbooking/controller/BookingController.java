package com.booking_system.ticketbooking.controller;

import com.booking_system.ticketbooking.dto.BookingResponse;
import com.booking_system.ticketbooking.dto.CreateBookingRequest;
import com.booking_system.ticketbooking.entity.Booking;
import com.booking_system.ticketbooking.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingResponse> bookEvent(@RequestBody @Valid CreateBookingRequest createBookingRequest){
        Booking booking = bookingService.bookEvent(createBookingRequest);
        BookingResponse bookingResponse = new BookingResponse();
        bookingResponse.setAmount(booking.getEventSeat().getPrice());
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!! Booking entity ID: " + booking.getBookingId());
        bookingResponse.setBookingId(booking.getBookingId());
        System.out.println(
                "Response DTO ID: " + bookingResponse.getBookingId()
        );
        bookingResponse.setBookingStatus(booking.getBookingStatus());
        bookingResponse.setCreatedAt(booking.getCreatedAt());
        bookingResponse.setEventId(booking.getEventSeat().getEvent().getEventId());
        bookingResponse.setEventName(booking.getEventSeat().getEvent().getName());
        bookingResponse.setSeatId(booking.getEventSeat().getSeat().getSeatId());
        bookingResponse.setSeatNumber(booking.getEventSeat().getSeat().getSeatNumber());
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingResponse);
    }

    @PostMapping("/{bookingId}/cancel")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long bookingId){
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.noContent().build();
    }

    public Page<BookingResponse> convertBookingToBookingResponse(Page<Booking> bookingPages){
        return bookingPages.map(booking ->{
            BookingResponse bookingResponse = new BookingResponse();
            bookingResponse.setAmount(booking.getEventSeat().getPrice());
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!! Booking entity ID: " + booking.getBookingId());
            bookingResponse.setBookingId(booking.getBookingId());
            System.out.println(
                    "Response DTO ID: " + bookingResponse.getBookingId()
            );
            bookingResponse.setBookingStatus(booking.getBookingStatus());
            bookingResponse.setCreatedAt(booking.getCreatedAt());
            bookingResponse.setEventId(booking.getEventSeat().getEvent().getEventId());
            bookingResponse.setEventName(booking.getEventSeat().getEvent().getName());
            bookingResponse.setSeatId(booking.getEventSeat().getSeat().getSeatId());
            bookingResponse.setSeatNumber(booking.getEventSeat().getSeat().getSeatNumber());
            return bookingResponse;
        });
    }
    @GetMapping
    public Page<BookingResponse> fetchBookingByUser(@RequestParam Long userId,@PageableDefault(page=0,size=10) Pageable pageable){
        int newPageSize = Math.min(pageable.getPageSize(),5);
        Set<String> allowedSortFields = Set.of("createdAt","bookingId");
        Sort sort = pageable.getSort();
        for(Sort.Order order: sort){
            if(!allowedSortFields.contains(order.getProperty())){
                throw new IllegalArgumentException("Unsupported Sort field - "+order.getProperty());
            }
        }

        Pageable safePageable = PageRequest.of(pageable.getPageNumber(),newPageSize,sort);
//        Page<Booking> bookingPages = bookingService.fetchBookingByUser(userId,safePageable);
//        return convertBookingToBookingResponse(bookingPages);
        return bookingService.fetchBookingByUser(userId,pageable);
    }

}
