package com.design.bookmyshow.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class Theatre {
	private final Integer theatreId; 
	private final String theatreName; 
	private final List<Screen> screens; 
	
	public Theatre(@NonNull final Integer theatreId,@NonNull final String theatreName) {
		this.theatreId = theatreId; 
		this.theatreName = theatreName; 
		this.screens = new ArrayList<>(); 
	}
	
	public void addScreen(@NonNull final Screen screen) {
		this.screens.add(screen);
	}
	
}
