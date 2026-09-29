package com.booking_system.ticketbooking.repository;

import com.booking_system.ticketbooking.entity.Payment;
import com.booking_system.ticketbooking.entity.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    boolean existsByBooking_BookingIdAndPaymentStatus(Long bookingId, PaymentStatus paymentStatus);
    Optional<Payment> findByBooking_BookingIdAndPaymentStatus(Long bookingId,PaymentStatus paymentStatus);
}
