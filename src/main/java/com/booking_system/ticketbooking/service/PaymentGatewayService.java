package com.booking_system.ticketbooking.service;

import com.booking_system.ticketbooking.dto.PaymentGatewayRequest;
import com.booking_system.ticketbooking.dto.PaymentGatewayResponse;
import com.booking_system.ticketbooking.entity.PaymentStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentGatewayService {
    // call external gateway
    // returns the gateway payment reference
    public PaymentGatewayResponse initiatePayment(PaymentGatewayRequest paymentGatewayRequest){
        PaymentGatewayResponse paymentGatewayResponse = new PaymentGatewayResponse();
        paymentGatewayResponse.setPaymentReference("ABC");
        paymentGatewayResponse.setPaymentStatus(PaymentStatus.INITIATED);
        return paymentGatewayResponse;
    }

}
