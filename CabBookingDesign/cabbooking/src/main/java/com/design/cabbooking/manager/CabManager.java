package com.design.cabbooking.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.design.cabbooking.exception.CabAlreadyExistsException;
import com.design.cabbooking.exception.CabNotFoundException;
import com.design.cabbooking.model.Cab;
import com.design.cabbooking.model.Location;

import lombok.NonNull;

public class CabManager {
	
	Map<String,Cab> cabs = new HashMap<>(); 
	
	public void createCab(@NonNull final Cab newCab) {
		if(cabs.containsKey(newCab.getId())) {
			throw new CabAlreadyExistsException(); 
		}
		cabs.put(newCab.getId(), newCab); 
	}
	
	public Cab getCab(@NonNull final String cabId) {
		if(!cabs.containsKey(cabId)) {
			throw new CabNotFoundException(); 
		}
		return cabs.get(cabId); 
	}
	
	public void updateCabLocation(@NonNull final String cabId, @NonNull final Location newLocation) {
		if(!cabs.containsKey(cabId)) {
			throw new CabNotFoundException(); 
		} 
		cabs.get(cabId).setLocation(newLocation);
	}
	
	public void updateCabAvailability(@NonNull final String cabId, @NonNull final Boolean newAvailability) {
		if(!cabs.containsKey(cabId)) {
			throw new CabNotFoundException(); 
		}
		cabs.get(cabId).setIsAvailable(newAvailability);
	}
	
	public List<Cab> getCabs(@NonNull final Location from, @NonNull final Double distance) {
		List<Cab> result = new ArrayList<>(); 
		for(Cab cab: cabs.values()) {
			if(cab.getIsAvailable() && cab.getLocation().distance(from) <= distance) {
				result.add(cab); 
			}
		}
		return result; 
	}
	
	
	
}
