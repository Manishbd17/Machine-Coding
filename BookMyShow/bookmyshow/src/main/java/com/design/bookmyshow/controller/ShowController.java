package com.design.bookmyshow.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.design.bookmyshow.model.Movie;
import com.design.bookmyshow.model.Screen;
import com.design.bookmyshow.model.Seat;
import com.design.bookmyshow.model.Show;
import com.design.bookmyshow.service.MovieService;
import com.design.bookmyshow.service.SeatAvailabilityService;
import com.design.bookmyshow.service.ShowService;
import com.design.bookmyshow.service.TheatreService;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class ShowController {
	
	private final ShowService showService; 
	private final TheatreService theatreService;
	private final MovieService movieService; 
	private final SeatAvailabilityService seatAvailabilityService;
	
	public Integer createShow(@NonNull final Integer movieId, @NonNull final Integer screenId, @NonNull final Date startTime,@NonNull final Integer durationInSeconds) {
		final Screen screen = theatreService.getScreen(screenId); 
		final Movie movie = movieService.getMovie(movieId); 
		return showService.createShow(movie,screen,startTime,durationInSeconds).getShowId(); 
	}
	
	public List<Integer> geAvailableSeats(@NonNull final Integer showId) {
		final Show show = showService.getShow(showId);
		final List<Seat> availableSeats = seatAvailabilityService.getAvailableSeats(show);
		return availableSeats.stream().map(Seat::getSeatId).collect(Collectors.toList()); 
	}
}
