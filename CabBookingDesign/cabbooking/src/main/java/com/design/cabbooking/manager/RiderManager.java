package com.design.cabbooking.manager;

import java.util.HashMap;
import java.util.Map;

import com.design.cabbooking.exception.RiderAlreadyExistsException;
import com.design.cabbooking.exception.RiderNotFoundException;
import com.design.cabbooking.model.Rider;

import lombok.NonNull;

public class RiderManager {
	
	Map<String,Rider> riders = new HashMap<>(); 
	
	public void createRider(@NonNull final Rider newRider) {
		if(riders.containsKey(newRider.getId())) {
			throw new RiderAlreadyExistsException(); 
		}
		riders.put(newRider.getId(), newRider); 
	}
	
	public Rider getRider(@NonNull final String riderId) { 
		if(!riders.containsKey(riderId)) {
			throw new RiderNotFoundException(); 
		}
		return riders.get(riderId); 
	}
}
