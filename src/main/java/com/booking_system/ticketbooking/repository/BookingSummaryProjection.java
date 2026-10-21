package com.booking_system.ticketbooking.repository;

import com.booking_system.ticketbooking.entity.BookingStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface BookingSummaryProjection {
    Long getBookingId();
    BookingStatus getBookingStatus();
    LocalDateTime getCreatedAt();

    Long getEventId();
    Long getSeatId();

    String getEventName();
    String getSeatNumber();

    BigDecimal getAmount();
}
