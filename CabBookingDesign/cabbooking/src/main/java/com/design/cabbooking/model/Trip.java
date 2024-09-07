package com.design.cabbooking.model;

import lombok.NonNull;
import lombok.ToString;

@ToString
public class Trip {
	
	private Rider rider;
	private TripStatus status; 
	private Cab cab; 
	private Trip trip; 
	private Double price; 
	private Location from; 
	private Location to; 
	
	public Trip(@NonNull final Rider rider,
		      @NonNull final Cab cab,
		      @NonNull final Double price,
		      @NonNull final Location from,
		      @NonNull final Location to) {
		this.rider = rider ;
		this.cab = cab; 
		this.price = price; 
		this.from = from ;
		this.to = to; 
		this.status = status.IN_PROGRESS;
	}
	
	public void endTrip() { 
		this.status=status.FINISHED; 
	}
	
	
	
	
	
	
	
}
