package com.design.cabbooking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import com.design.cabbooking.manager.CabManager;
import com.design.cabbooking.manager.TripManager;
import com.design.cabbooking.model.Cab;

public class CabController {
	private CabManager cabManager; 
	private TripManager tripManger;
	
	@PostMapping("/cab/register")
	public ResponseEntity registerCab(final String cabId,final String name) {
		cabManager.createCab(new Cab(cabId,name)); 
		return ResponseEntity.ok(""); 
	}
	
	@PostMapping("/cab/update/location")
	public ResponseEntity updateCabLocation(final String cabId,final double newX,final double newY) {
		cabManager.updateCabLocation(cabId, new Location(newX,newY)); 
		return ResponseEntity.ok("");
	}
	
	@PostMapping("/cab/update/availability")
	public ResponseEntity updateCabAvailability(final String cabId,final Boolean newAvailability) {
		cabManager.updateCabAvailability(cabId, newAvailability); 
		return ResponseEntity.ok("");
	}
	
	@PostMapping("/cab/endTrip")
	public ResponseEntity endTrip(final String cabId) {
		tripManger.endTrip(cabManager.getCab(cabId)); 
		return ResponseEntity.ok(""); 
	}
	
	
	
}
