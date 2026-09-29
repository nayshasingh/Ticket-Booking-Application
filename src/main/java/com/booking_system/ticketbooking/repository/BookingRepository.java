package com.booking_system.ticketbooking.repository;

import com.booking_system.ticketbooking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
}
