package com.booking_system.ticketbooking.repository;

import com.booking_system.ticketbooking.entity.Booking;
import com.booking_system.ticketbooking.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {
    @Modifying
    @Query(
            """
            UPDATE Booking b
            SET b.bookingStatus = BookingStatus.CANCELLED
            WHERE b.bookingId = :bookingId
            AND b.bookingStatus = BookingStatus.PENDING
            """
    )
    public int cancelBooking(@Param("bookingId") Long bookingId);

    @EntityGraph(attributePaths = {
            "eventSeat",
            "eventSeat.event",
            "eventSeat.seat"
    })
    public Page<Booking> findByUser_UserId(Long userId, Pageable pageable);

    Long user(User user);

    @Query("""
        SELECT 
            b.bookingId AS bookingId,
            b.bookingStatus AS bookingStatus,
            b.createdAt AS createdAt,
            e.eventId AS eventId,
            s.seatId AS seatId,
            e.name AS eventName,
            s.seatNumber AS seatNumber,
            es.price AS amount
        FROM Booking b
        JOIN b.eventSeat es
        JOIN es.event e
        JOIN es.seat s
        WHERE b.user.userId = :userId
""")
    public Page<BookingSummaryProjection>findBookingSummariesByUserId(Long userId,Pageable pageable);
}
