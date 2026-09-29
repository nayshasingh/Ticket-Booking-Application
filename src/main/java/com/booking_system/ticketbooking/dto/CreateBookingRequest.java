package com.booking_system.ticketbooking.dto;

import jakarta.validation.constraints.NotNull;

public class CreateBookingRequest {
    @NotNull
    Long userId;
    @NotNull
    Long eventId;
    @NotNull
    Long seatId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }
}
