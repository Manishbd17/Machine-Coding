package com.design.bookmyshow.controller;

import com.design.bookmyshow.service.MovieService;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class MovieController {
	
	private final MovieService movieService; 
	
	public Integer createMovie(@NonNull final String movieName) {
		return movieService.createMovie(movieName).getMovieId(); 
	}
}
