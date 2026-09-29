package com.booking_system.ticketbooking.repository;

import com.booking_system.ticketbooking.entity.EventSeat;
import com.booking_system.ticketbooking.entity.EventSeatId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EventSeatRepository extends JpaRepository<EventSeat, EventSeatId> {

    @Modifying
    @Query("""
           UPDATE EventSeat es
           SET es.status = Status.BOOKED
           WHERE es.event.eventId=:eventId
           AND es.seat.seatId=:seatId
           AND es.status = Status.AVAILABLE
           """)
    public int bookSeat(@Param("eventId") long eventId,
                        @Param("seatId") long seatId);

    @Modifying
    @Query("""
            UPDATE EventSeat es
            SET es.status = Status.AVAILABLE
            WHERE es.event.eventId = :eventId
            AND es.seat.seatId = :seatId
            AND es.status = Status.BOOKED
            """)
    public int releaseSeat(@Param("eventId") long eventId,@Param("seatId") long seatId);
}
