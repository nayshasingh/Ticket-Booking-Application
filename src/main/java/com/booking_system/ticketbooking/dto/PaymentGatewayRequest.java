package com.booking_system.ticketbooking.dto;

import com.booking_system.ticketbooking.entity.PaymentMethod;

import java.math.BigDecimal;

public class PaymentGatewayRequest {
    Long paymentId;
    BigDecimal amount;
    PaymentMethod paymentMethod;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
