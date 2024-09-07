package com.design.cabbooking.strategy;

import com.design.cabbooking.model.Location;

public class DefaultPriceStrategy implements PriceStrategy {

	public static final Double PER_KM_RATE = 10.0; 
	
	@Override
	public Double findPrice(Location from, Location to) {
		return from.distance(to)*PER_KM_RATE;
	}

}
