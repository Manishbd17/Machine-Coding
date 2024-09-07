package com.design.cabbooking.strategy;

import com.design.cabbooking.model.Location;

public interface PriceStrategy {
	Double findPrice(Location from,Location to); 
}
