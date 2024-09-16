package com.design.bookmyshow.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.design.bookmyshow.exception.BadRequestException;
import com.design.bookmyshow.exception.NotFoundException;
import com.design.bookmyshow.exception.SeatPermanentlyUnavailableException;
import com.design.bookmyshow.model.Booking;
import com.design.bookmyshow.model.Seat;
import com.design.bookmyshow.model.Show;
import com.design.bookmyshow.providers.SeatLockProvider;

import lombok.NonNull;

public class BookingService {
	
	private final Map<Integer,Booking> bookings; 
	private final SeatLockProvider seatLockProvider; 
	
	public BookingService(SeatLockProvider seatLockProvider) {
		this.seatLockProvider = seatLockProvider;
		this.bookings = new HashMap<>(); 
	}

	public Booking getBooking(@NonNull Integer bookingId) {
		if(!bookings.containsKey(bookingId)) {
			throw new NotFoundException();
		}
		return bookings.get(bookingId); 
	}

	public List<Booking> getAllBookings(@NonNull final Show show) {
		List<Booking> response = new ArrayList<>(); 
		for(Booking booking: bookings.values()) {
			if(booking.getShow().equals(show)) {
				response.add(booking); 
			}
		}
		return response; 
	}
	
	public Booking createBooking(@NonNull Integer userId, Show show, List<Seat> seats) {
		if(isAnySeatAlreadyBooked(show,seats)) {
			throw new SeatPermanentlyUnavailableException(); 
		}
		seatLockProvider.lockSeats(show, seats, userId);
		final Integer bookingId = Integer.parseInt(UUID.randomUUID().toString()); 
		final Booking newBooking = new Booking(bookingId,show,seats,userId);
		bookings.put(bookingId, newBooking); 
		return newBooking;
	}

	public List<Seat> getBookedSeats(@NonNull final Show show) {
		return getAllBookings(show).stream().filter(Booking::isConfirmed).map(Booking::getSeatsBooked)
				.flatMap(Collection::stream).collect(Collectors.toList()); 
	}
	
	public void confirmBooking(@NonNull final Booking booking,@NonNull final Integer userId) {
		if(!booking.getUserId().equals(userId)) {
			throw new BadRequestException(); 
		}
		for(Seat seat: booking.getSeatsBooked()) {
			if(!seatLockProvider.validateLock(booking.getShow(), seat, userId)) {
				throw new BadRequestException(); 
			}
		}
		booking.confirmBooking();
	}
	
	private boolean isAnySeatAlreadyBooked(@NonNull final Show show, final List<Seat> seats) {
		final List<Seat> bookedSeats = getBookedSeats(show); 
		for(Seat seat: bookedSeats) {
			if(bookedSeats.contains(seat)) {
				return true; 
			}
		}
		return false; 
	}


}
