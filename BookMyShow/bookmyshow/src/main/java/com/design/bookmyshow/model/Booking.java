package com.design.bookmyshow.model;

import java.util.List;

import com.design.bookmyshow.exception.InvalidStateException;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class Booking {
	private final Integer bookingId;
	private final Show show; 
	private final List<Seat> seatsBooked; 
	private final Integer userId;
	private BookingStatus bookingStatus;
	
	public Booking(@NonNull final Integer bookingId, @NonNull final Show show,@NonNull  final List<Seat> seatsBooked,@NonNull final Integer userId) {
		this.bookingId = bookingId; 
		this.show = show; 
		this.seatsBooked = seatsBooked;
		this.userId = userId;
		this.bookingStatus = BookingStatus.CREATED;
	}
	
	public boolean isConfirmed() {
		return this.bookingStatus == BookingStatus.CONFIRMED;
	}
	
	public void confirmBooking() {
		if(this.bookingStatus != BookingStatus.CREATED) {
			throw new InvalidStateException(); 
		}
		this.bookingStatus = BookingStatus.CONFIRMED; 
	}
	
	public void expireBooking() {
		if(this.bookingStatus != BookingStatus.CREATED)  {
			throw new InvalidStateException();
		}
		this.bookingStatus = BookingStatus.EXPIRED; 
	}
}
