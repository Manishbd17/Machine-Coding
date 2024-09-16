package com.design.bookmyshow.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class Screen {
    private final Integer screenId; 
    private final String screenName; 
    private final Theatre theatre; 
    private final List<Seat> seats;
    
    public Screen(@NonNull Integer screenId, @NonNull final String screenName, @NonNull final Theatre theatre) {
    	this.screenId= screenId;
    	this.screenName = screenName;
    	this.theatre = theatre; 
    	this.seats = new ArrayList<>(); 
    }
    
    public void addSeat(@NonNull final Seat seat) {
    	this.seats.add(seat); 
    }
}
