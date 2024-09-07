package com.design.cabbooking.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class Cab {
	String id; 
    String name; 
	
	@Setter Trip currentTrip; 
	@Setter Location location; 
	@Setter Boolean isAvailable; 
	
	public Cab(String id,String name) {
		this.id=id; 
		this.name=name; 
		this.isAvailable=true; 
	}
	
}
