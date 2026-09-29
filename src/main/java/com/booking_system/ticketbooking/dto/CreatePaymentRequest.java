package com.booking_system.ticketbooking.dto;


import com.booking_system.ticketbooking.entity.PaymentMethod;
import jakarta.validation.constraints.NotNull;

public class CreatePaymentRequest {
    @NotNull
    Long bookingId;
    @NotNull
    private PaymentMethod paymentMethod;

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }
}
