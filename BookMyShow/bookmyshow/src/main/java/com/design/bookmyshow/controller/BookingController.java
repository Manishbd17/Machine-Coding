package com.design.bookmyshow.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.design.bookmyshow.model.Seat;
import com.design.bookmyshow.model.Show;
import com.design.bookmyshow.service.BookingService;
import com.design.bookmyshow.service.ShowService;
import com.design.bookmyshow.service.TheatreService;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class BookingController {
	private final BookingService bookingService;
	private final ShowService showService;
	private final TheatreService theatreService;
	
	public Integer createBooking(@NonNull final Integer userId,@NonNull final Integer showId,@NonNull final List<Integer> seatIds) {
		final Show show = showService.getShow(showId); 
		final List<Seat> seats = seatIds.stream().map(theatreService::getSeat).collect(Collectors.toList()); 
		return bookingService.createBooking(userId,show,seats).getBookingId();  
	}
}
