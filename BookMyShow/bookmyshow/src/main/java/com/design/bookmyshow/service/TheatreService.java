package com.design.bookmyshow.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.design.bookmyshow.exception.NotFoundException;
import com.design.bookmyshow.model.Screen;
import com.design.bookmyshow.model.Seat;
import com.design.bookmyshow.model.Theatre;

import lombok.NonNull;

public class TheatreService {
	
	private final Map<Integer,Theatre> theatres; 
	private final Map<Integer,Screen> screens; 
	private final Map<Integer,Seat> seats; 
	
	public TheatreService() {
		this.theatres = new HashMap<>();
		this.screens = new HashMap<>(); 
		this.seats = new HashMap<>(); 
	}
	
	public Seat getSeat(@NonNull final Integer seatId) {
		if(!seats.containsKey(seatId)) {
			throw new NotFoundException();
		}
		return seats.get(seatId);
	}
	
	public Screen getScreen(@NonNull final Integer screenId) {
		if(!screens.containsKey(screenId)) {
			throw new NotFoundException(); 
		}
		return screens.get(screenId); 
	}
	
	public Theatre getTheatre(@NonNull final Integer theatreId) {
		if(!theatres.containsKey(theatreId)) {
			throw new NotFoundException(); 
		}
		return theatres.get(theatreId); 
	}

	public Theatre createTheatre(@NonNull final String theatreName) {
		Integer theatreId = Integer.parseInt(UUID.randomUUID().toString()); 
		Theatre theatre = new Theatre(theatreId,theatreName); 
		theatres.put(theatreId, theatre); 
		return theatre; 
	}

	public Screen createScreenInTheatre(@NonNull final String screenName,@NonNull final  Theatre theatre) {
		Integer screenId = Integer.parseInt(UUID.randomUUID().toString()); 
		Screen screen = new Screen(screenId,screenName,theatre); 
		screens.put(screenId, screen);
		theatre.addScreen(screen);
		return screen; 
	}

	public Seat createSeatInScreen(@NonNull final Integer rowNo, @NonNull final Integer seatNo, @NonNull final Screen screen) {
		Integer seatId = Integer.parseInt(UUID.randomUUID().toString()); 
		Seat seat = new Seat(seatId,rowNo,seatNo);
		seats.put(seatId, seat);
		screen.addSeat(seat);
		return seat;
	}
	
}
