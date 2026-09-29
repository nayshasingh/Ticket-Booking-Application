package com.booking_system.ticketbooking.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="event_id",referencedColumnName = "event_id",nullable = false),
            @JoinColumn(name="seat_id",referencedColumnName = "seat_id",nullable = false)
    })
    private EventSeat eventSeat;
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EventSeat getEventSeat() {
        return eventSeat;
    }

    public void setEventSeat(EventSeat eventSeat) {
        this.eventSeat = eventSeat;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
