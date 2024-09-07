package com.design.cabbooking.strategy;

import java.util.List;

import com.design.cabbooking.model.Cab;
import com.design.cabbooking.model.Location;
import com.design.cabbooking.model.Rider;

public interface CabMatchingStrategy {
	Cab matchCabToRider(Rider rider,List<Cab> candidateCabs,Location from,Location to);
}
