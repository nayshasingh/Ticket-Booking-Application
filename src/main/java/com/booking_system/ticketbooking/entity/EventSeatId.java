package com.booking_system.ticketbooking.entity;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class EventSeatId {
    private Long eventId;
    private Long seatId;

    public EventSeatId() {
    }

    public EventSeatId(Long eventId, Long seatId) {
        this.eventId = eventId;
        this.seatId = seatId;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)return true;
        if(obj == null || getClass() != obj.getClass())return false;
        EventSeatId that = (EventSeatId) obj;

        return Objects.equals(this.eventId,that.eventId) && Objects.equals(this.seatId,that.seatId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId,seatId);
    }
}
