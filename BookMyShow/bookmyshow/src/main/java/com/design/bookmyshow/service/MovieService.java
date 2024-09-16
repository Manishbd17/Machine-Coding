package com.design.bookmyshow.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.design.bookmyshow.exception.NotFoundException;
import com.design.bookmyshow.model.Movie;

import lombok.NonNull;

public class MovieService {
	
	private final Map<Integer,Movie> movies; 
	
	public MovieService() {
		this.movies = new HashMap<>();
	}

	public Movie getMovie(@NonNull final Integer movieId) {
		if(!movies.containsKey(movieId)) {
			throw new NotFoundException(); 
		}
		return movies.get(movieId); 
	}
	
	public Movie createMovie(@NonNull String movieName) {
		Integer movieId = Integer.parseInt(UUID.randomUUID().toString()); 
		Movie movie = new Movie(movieId,movieName); 
		movies.put(movieId, movie); 
		return movie; 
	}

}
