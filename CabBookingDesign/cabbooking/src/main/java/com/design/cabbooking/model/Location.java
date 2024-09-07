package com.design.cabbooking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class Location {
	private Double x; 
	private Double y; 
	
	public Double distance(Location loc) {
		return Math.sqrt( Math.pow(this.x-loc.x,2)  + Math.pow(this.y-loc.y,2) ); 
	}
}
