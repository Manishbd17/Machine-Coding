package com.design.cabbooking.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.design.cabbooking.exception.NoCabsAvailableException;
import com.design.cabbooking.exception.TripNotFoundException;
import com.design.cabbooking.model.Cab;
import com.design.cabbooking.model.Location;
import com.design.cabbooking.model.Rider;
import com.design.cabbooking.model.Trip;
import com.design.cabbooking.strategy.CabMatchingStrategy;
import com.design.cabbooking.strategy.PriceStrategy;

import lombok.NonNull;

public class TripManager {
	
	public static final Double MAX_ALLOWED_TRIP_MATCHING_DISTANCE = 10.0; 
	Map<String,List<Trip>> trips = new HashMap<>(); 
	private CabManager cabManager; 
	private RiderManager riderManager; 
	private PriceStrategy priceStrategy; 
	private CabMatchingStrategy cabMatchingStrategy; 
	
	public TripManager(CabManager cabManager,RiderManager riderManager,PriceStrategy priceStrategy,CabMatchingStrategy cabMatchingStrategy) {
		this.cabManager = cabManager;
		this.riderManager = riderManager;
		this.priceStrategy = priceStrategy; 
		this.cabMatchingStrategy = cabMatchingStrategy; 
	}	
	
	public void createTrip(@NonNull final Rider rider,@NonNull final Location from,@NonNull Location to) {
		List<Cab> closeByCabs = cabManager.getCabs(from, MAX_ALLOWED_TRIP_MATCHING_DISTANCE); 
		List<Cab> closeByAvailableCabs = closeByCabs.stream().filter(cab -> cab.getCurrentTrip()==null).collect(Collectors.toList()); 
		final Cab selectedCab = cabMatchingStrategy.matchCabToRider(rider, closeByAvailableCabs, from, to); 
		if(selectedCab==null) {
			throw new NoCabsAvailableException();
		}
		final Double price = priceStrategy.findPrice(from, to); 
		final Trip newTrip = new Trip(rider,selectedCab,price,from,to);
		if(!trips.containsKey(rider.getId())) {
			trips.put(rider.getId(), new ArrayList<>()); 
		}
		trips.get(rider.getId()).add(newTrip); 
		selectedCab.setCurrentTrip(newTrip);
	}
	
	public List<Trip> tripHistory(@NonNull final Rider rider) {
		return trips.get(rider.getId()); 
	}
	
	public void endTrip(@NonNull final Cab cab) {
		if(cab.getCurrentTrip()==null) {
			throw new TripNotFoundException(); 
		}
		cab.getCurrentTrip().endTrip(); 
		cab.setCurrentTrip(null);
	}
		
}
