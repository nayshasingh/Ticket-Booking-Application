package com.booking_system.ticketbooking.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class EventSeat {
    @EmbeddedId
    private EventSeatId id;

    @ManyToOne
    @MapsId("eventId")
    @JoinColumn(name="event_id",nullable = false)
    private Event event;

    @ManyToOne
    @MapsId("seatId")
    @JoinColumn(name="seat_id",nullable = false)
    private Seat seat;

    @Enumerated(EnumType.STRING)
    private Status status;

    private BigDecimal price;

    public EventSeatId getId() {
        return id;
    }

    public void setId(EventSeatId id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
