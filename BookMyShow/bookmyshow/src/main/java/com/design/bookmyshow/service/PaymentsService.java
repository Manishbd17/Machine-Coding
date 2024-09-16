package com.design.bookmyshow.service;

import java.util.HashMap;
import java.util.Map;

import com.design.bookmyshow.exception.BadRequestException;
import com.design.bookmyshow.model.Booking;
import com.design.bookmyshow.providers.SeatLockProvider;

import lombok.NonNull;

public class PaymentsService {
	
	private final Map<Booking,Integer> bookingFailures;
	private final Integer allowedRetries; 
	private final SeatLockProvider seatLockProvider; 

	public PaymentsService(Integer allowedRetries,SeatLockProvider seatLockProvider) {
		this.allowedRetries = allowedRetries; 
		this.seatLockProvider = seatLockProvider;
		this.bookingFailures = new HashMap<>();
	}
	
	public void processPaymentsFailed(@NonNull final Booking booking, @NonNull Integer userId) {
		if(!booking.getUserId().equals(userId)) {
			throw new BadRequestException(); 
		}
		if(!bookingFailures.containsKey(booking)) {
			bookingFailures.put(booking, 0); 
		}
		final Integer currentFailureCount = bookingFailures.get(booking); 
		final Integer newFailureCount = currentFailureCount + 1;
		bookingFailures.put(booking, newFailureCount); 
		if(newFailureCount > allowedRetries) {
			seatLockProvider.unlockSeats(booking.getShow(), booking.getSeatsBooked(), userId);
		}
	} 
	
}
