package com.design.parkinglot.parking.strategy;

import java.util.TreeSet;

import com.design.parkinglot.exception.NoFreeSlotException;

public class NaturalParkingOrderStrategy implements ParkingStrategy {
	
	TreeSet<Integer> slotTreeSet; 
	
	public NaturalParkingOrderStrategy() {
		this.slotTreeSet = new TreeSet<>(); 
	}

	@Override
	public void addSlot(Integer slotNumber) {
		this.slotTreeSet.add(slotNumber); 
	}

	@Override
	public void removeSlot(Integer slotNumber) {
		this.slotTreeSet.remove(slotNumber); 
	}

	@Override
	public Integer getNextSlot() {
		if(slotTreeSet.isEmpty()) {
			throw new NoFreeSlotException(); 
		}
		return this.slotTreeSet.first();
	}

}
