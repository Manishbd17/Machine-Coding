package com.design.cabbooking.strategy;

import java.util.List;

import com.design.cabbooking.model.Cab;
import com.design.cabbooking.model.Location;
import com.design.cabbooking.model.Rider;

import lombok.NonNull;

public class DefaultCabMatchingStrategy implements CabMatchingStrategy {
	
	@Override
	public Cab matchCabToRider(@NonNull final Rider rider,
			@NonNull final List<Cab> candidateCabs,@NonNull final Location from,@NonNull final Location to) {
		if(candidateCabs.isEmpty()) {
			return null; 
		}
		return candidateCabs.get(0);
	}
}
