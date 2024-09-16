package com.design.bookmyshow.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.design.bookmyshow.exception.NotFoundException;
import com.design.bookmyshow.exception.ScreenAlreadyOccupiedException;
import com.design.bookmyshow.model.Movie;
import com.design.bookmyshow.model.Screen;
import com.design.bookmyshow.model.Show;

import lombok.NonNull;

public class ShowService {
	
	private final Map<Integer,Show> shows; 
	
	public ShowService() {
		this.shows = new HashMap<>(); 
	}

	public Show getShow(@NonNull final Integer showId) {
		if(!shows.containsKey(showId)) {
			throw new NotFoundException(); 
		}
		return shows.get(showId); 
	}
	
	public Show createShow(Movie movie, Screen screen, @NonNull Date startTime, @NonNull Integer durationInSeconds) {
		if(!checkIfShowCreationAllowed(screen,startTime,durationInSeconds)) {
			throw new ScreenAlreadyOccupiedException();
		}
		Integer showId = Integer.parseInt(UUID.randomUUID().toString()); 
		Show show = new Show(showId,movie,screen,startTime,durationInSeconds); 
		shows.put(showId, show);
		return show;
	}

	private boolean checkIfShowCreationAllowed(Screen screen, @NonNull Date startTime,@NonNull Integer durationInSeconds) {
		return true;
	}

	public List<Show> getShowsForScreen(@NonNull final Screen screen) {
		final List<Show> response = new ArrayList<>();
		for(Show show : shows.values()) {
			if(show.getScreen().equals(screen)) {
				response.add(show);
			}
		}
		return response; 
	}
}
