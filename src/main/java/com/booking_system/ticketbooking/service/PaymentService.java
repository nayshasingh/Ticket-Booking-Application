package com.booking_system.ticketbooking.service;

import com.booking_system.ticketbooking.dto.CreatePaymentRequest;
import com.booking_system.ticketbooking.dto.PaymentGatewayCallbackRequest;
import com.booking_system.ticketbooking.dto.PaymentGatewayRequest;
import com.booking_system.ticketbooking.dto.PaymentGatewayResponse;
import com.booking_system.ticketbooking.entity.Booking;
import com.booking_system.ticketbooking.entity.BookingStatus;
import com.booking_system.ticketbooking.entity.Payment;
import com.booking_system.ticketbooking.entity.PaymentStatus;
import com.booking_system.ticketbooking.exception.*;
import com.booking_system.ticketbooking.repository.BookingRepository;
import com.booking_system.ticketbooking.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PaymentService {
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentGatewayService paymentGatewayService;

    public PaymentService(BookingRepository bookingRepository, PaymentRepository paymentRepository, PaymentGatewayService paymentGatewayService) {
        this.bookingRepository = bookingRepository;
        this.paymentRepository = paymentRepository;
        this.paymentGatewayService = paymentGatewayService;
    }
    @Transactional
    public Payment createPayment(CreatePaymentRequest createPaymentRequest){
        // from booking id fetch the booking
        // from booking . eventSeat fetch the price of seat
        Booking booking = bookingRepository.findById(createPaymentRequest.getBookingId()).orElseThrow(()-> new BookingDoesNotExistException("Booking does not exist!"));
        if(booking.getBookingStatus() != BookingStatus.PENDING){
            throw new InvalidBookingStatusException("Payment can only be done for pending booking");
        }

        Optional<Payment> successfulPayment = paymentRepository.findByBooking_BookingIdAndPaymentStatus(booking.getBookingId(), PaymentStatus.SUCCESS);
        if(successfulPayment.isPresent())return successfulPayment.get();

        Optional<Payment> initiatedPayment = paymentRepository.findByBooking_BookingIdAndPaymentStatus(booking.getBookingId(), PaymentStatus.INITIATED);
        if(initiatedPayment.isPresent()){
            throw new PaymentAlreadyInitiatedException("Payment is in progress...");
        }
        BigDecimal amount = booking.getEventSeat().getPrice();
        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setAmount(amount);
        payment.setPaymentMethod(createPaymentRequest.getPaymentMethod());
        payment.setCreatedAt(LocalDateTime.now());
        payment.setPaymentStatus(PaymentStatus.PENDING);

        Payment savedPayment = paymentRepository.save(payment);

        // after saving payment, call payment gateway with paymentId created
        PaymentGatewayRequest paymentGatewayRequest = new PaymentGatewayRequest();
        paymentGatewayRequest.setPaymentId(savedPayment.getPaymentId());
        paymentGatewayRequest.setAmount(savedPayment.getAmount());
        paymentGatewayRequest.setPaymentMethod(savedPayment.getPaymentMethod());
        PaymentGatewayResponse paymentGatewayResponse = paymentGatewayService.initiatePayment(paymentGatewayRequest);

        // after you get response from the payment gateway, set in payment object
        savedPayment.setPaymentReference(paymentGatewayResponse.getPaymentReference());
        savedPayment.setPaymentStatus(paymentGatewayResponse.getPaymentStatus());

        return paymentRepository.save(savedPayment);
    }

    @Transactional
    public Payment handleCallBackRequest(PaymentGatewayCallbackRequest paymentGatewayCallbackRequest){
        Payment payment = paymentRepository.findById(paymentGatewayCallbackRequest.getPaymentId()).orElseThrow(()->new PaymentDoesNotExistException("Payment does not exist"));
        if(!payment.getPaymentReference().equals(paymentGatewayCallbackRequest.getPaymentReference())){
            throw new InvalidPaymentReferenceException("Payment with give reference id does not exist");
        }
        if(payment.getPaymentStatus() != PaymentStatus.INITIATED){
            if(payment.getPaymentStatus() == paymentGatewayCallbackRequest.getPaymentStatus())return payment;
            throw new InvalidPaymentReferenceException("Invalid Payment State transition");
        }
        payment.setPaymentStatus(paymentGatewayCallbackRequest.getPaymentStatus());
        if(paymentGatewayCallbackRequest.getPaymentStatus() == PaymentStatus.SUCCESS){
            Booking booking = payment.getBooking();
            booking.setBookingStatus(BookingStatus.CONFIRMED);
            bookingRepository.save(booking); // not actually required as inside transaction this entity is managed by Hibernate
        }
        return paymentRepository.save(payment);
    }
}
