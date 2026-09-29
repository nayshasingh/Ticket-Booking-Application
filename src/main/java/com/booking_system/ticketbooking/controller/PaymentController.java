package com.booking_system.ticketbooking.controller;

import com.booking_system.ticketbooking.dto.CreatePaymentRequest;
import com.booking_system.ticketbooking.dto.PaymentGatewayCallbackRequest;
import com.booking_system.ticketbooking.entity.Payment;
import com.booking_system.ticketbooking.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public Payment createPayment(@RequestBody @Valid CreatePaymentRequest createPaymentRequest){
        return paymentService.createPayment(createPaymentRequest);
    }

    @PostMapping("/callback")
    public Payment handleCallback(@RequestBody @Valid PaymentGatewayCallbackRequest paymentGatewayCallbackRequest){
        return paymentService.handleCallBackRequest(paymentGatewayCallbackRequest);
    }
}
