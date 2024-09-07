package com.design.cabbooking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.design.cabbooking.manager.RiderManager;
import com.design.cabbooking.manager.TripManager;
import com.design.cabbooking.model.Location;
import com.design.cabbooking.model.Rider;

@RestController
public class RiderController {
	private RiderManager riderManager; 
	private TripManager tripManager; 
	
	public RiderController(RiderManager riderManager,TripManager tripManager) {
		this.riderManager = riderManager; 
		this.tripManager = tripManager; 
	}
	
	@PostMapping("/rider/register")
	public ResponseEntity registerRider(final String riderId,final String riderName) {
		riderManager.createRider(new Rider(riderId,riderName));
		return ResponseEntity.ok(""); 
	}
	
	@PostMapping("/tripsBooking")
	public ResponseEntity tripsBooking(final String riderId,final Double srcX,final Double srcY,final Double destX,final Double destY) {
		tripManager.createTrip(riderManager.getRider(riderId), new Location(srcX,srcY), new Location(destX,destY)); 
		return ResponseEntity.ok(""); 
	}
	
	@GetMapping("/tripsBooking") 
	public ResponseEntity tripsBooking(final String riderId) {
		List<trip> trips = tripManager.tripHistory(riderManager.getRider(riderId));
		return ResponseEntity.ok(trips);
	}
}
