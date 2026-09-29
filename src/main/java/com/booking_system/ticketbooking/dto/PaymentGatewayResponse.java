package com.booking_system.ticketbooking.dto;

import com.booking_system.ticketbooking.entity.PaymentStatus;

public class PaymentGatewayResponse {
    String paymentReference;
    PaymentStatus paymentStatus;

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
