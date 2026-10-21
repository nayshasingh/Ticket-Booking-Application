package com.booking_system.ticketbooking.controller;

import com.booking_system.ticketbooking.dto.CreatePaymentRequest;
import com.booking_system.ticketbooking.dto.PaymentGatewayCallbackRequest;
import com.booking_system.ticketbooking.dto.PaymentResponse;
import com.booking_system.ticketbooking.entity.Payment;
import com.booking_system.ticketbooking.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody @Valid CreatePaymentRequest createPaymentRequest){
        Payment payment = paymentService.createPayment(createPaymentRequest);
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setPaymentId(payment.getPaymentId());
        paymentResponse.setBookingId(payment.getBooking().getBookingId());
        paymentResponse.setAmount(payment.getAmount());
        paymentResponse.setCreatedAt(payment.getCreatedAt());
        paymentResponse.setPaymentMethod(payment.getPaymentMethod());
        paymentResponse.setPaymentReference(payment.getPaymentReference());
        paymentResponse.setPaymentStatus(payment.getPaymentStatus());
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentResponse);
    }

    @PostMapping("/callback")
    public Payment handleCallback(@RequestBody @Valid PaymentGatewayCallbackRequest paymentGatewayCallbackRequest){
        return paymentService.handleCallBackRequest(paymentGatewayCallbackRequest);
    }
}
