package com.design.bookmyshow.service;

import java.util.ArrayList;
import java.util.List;

import com.design.bookmyshow.model.Seat;
import com.design.bookmyshow.model.Show;
import com.design.bookmyshow.providers.SeatLockProvider;

import lombok.NonNull;

public class SeatAvailabilityService {
	
	private final BookingService bookingService;  
	private final SeatLockProvider seatLockProvider; 
	
	public SeatAvailabilityService(BookingService bookingService,SeatLockProvider seatLockProvider) {
		this.bookingService = bookingService; 
		this.seatLockProvider = seatLockProvider;
	}
	
	public List<Seat> getAvailableSeats(@NonNull final Show show) {
		final List<Seat> allSeats = show.getScreen().getSeats(); 
		final List<Seat> unavailableSeats = getUnAvailableSeats(show); 
		final List<Seat> availableSeats = new ArrayList<>(allSeats);
		availableSeats.removeAll(unavailableSeats); 
		return availableSeats;
	}
	
	public List<Seat> getUnAvailableSeats(@NonNull final Show show) {
		final List<Seat> unavailableSeats = new ArrayList<>(); 
		unavailableSeats.addAll(seatLockProvider.getLockedSeats(show));
		return unavailableSeats; 
	}
}
