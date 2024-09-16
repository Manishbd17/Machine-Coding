package com.design.bookmyshow.controller;

import com.design.bookmyshow.service.BookingService;
import com.design.bookmyshow.service.PaymentsService;

import lombok.NonNull;

public class PaymentsController {
	
	private final PaymentsService paymentsService; 
	private final BookingService bookingService; 
	
	public PaymentsController(PaymentsService paymentsService,BookingService bookingService) {
		this.paymentsService = paymentsService;
		this.bookingService = bookingService; 
	}
	
	public void paymentFailed(@NonNull final Integer bookingId, @NonNull final Integer userId) {
		paymentsService.processPaymentsFailed(bookingService.getBooking(bookingId),userId);
	}
	
	public void paymentSuccess(@NonNull final Integer bookingId, @NonNull final Integer userId) {
		bookingService.confirmBooking(bookingService.getBooking(bookingId), userId); 
	}
}
