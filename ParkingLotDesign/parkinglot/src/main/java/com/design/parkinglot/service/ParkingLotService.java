package com.design.parkinglot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.design.parkinglot.exception.ParkingLotException;
import com.design.parkinglot.model.Car;
import com.design.parkinglot.model.ParkingLot;
import com.design.parkinglot.model.Slot;
import com.design.parkinglot.parking.strategy.ParkingStrategy;

public class ParkingLotService {
	
	private ParkingLot parkingLot;
	private ParkingStrategy parkingStrategy; 

	//createParkingLot
	//park
	//makeSlotFree
	//getOccupiedSlot
	//validateParkingLotExists
	//getSlotsForColor
	
	public void createParkingLot(final ParkingLot parkingLot, final ParkingStrategy parkingStrategy) {
		if(this.parkingLot!=null) {
			throw new ParkingLotException("Parking Lot already exists"); 
		}
		this.parkingLot = parkingLot; 
		this.parkingStrategy = parkingStrategy; 
		for(int i=0;i<parkingLot.getCapacity();i++) {
			parkingStrategy.addSlot(i);
		}
	}
	
	public Integer park(final Car car) {
		validateParkingLotExists(); 
		final Integer nextFreeSlot = parkingStrategy.getNextSlot();
		parkingLot.park(car, nextFreeSlot); 
		parkingStrategy.removeSlot(nextFreeSlot);
		return nextFreeSlot; 
	}
	
	public void makeSlotFree(final Integer slotNumber) {
		validateParkingLotExists(); 
		parkingLot.makeSlotFree(slotNumber); 
		parkingStrategy.addSlot(slotNumber);
	}
	
	public List<Slot> getOccupiedSlots() {
		validateParkingLotExists(); 
		final List<Slot> occupiedSlotsList = new ArrayList<>();
		final Map<Integer,Slot> allSlots = parkingLot.getSlots();
		for(int i=0;i<parkingLot.getCapacity();i++) {
			if(allSlots.containsKey(i)) {
				final Slot slot = allSlots.get(i); 
				if(!slot.isSlotFree()) {
					occupiedSlotsList.add(slot);
				}
			}
		}
		return occupiedSlotsList; 
	}
	
	public void validateParkingLotExists() {
		if(parkingLot==null) {
			throw new ParkingLotException("Parking lot does not exists to park."); 
		}
	}
	
	public List<Slot> getSlotsForColor(final String color) {
		final List<Slot> occupiedSlotsList = getOccupiedSlots(); 
		return occupiedSlotsList.stream()
				.filter((slot) -> slot.getParkedCar().getColor().equals(color))
				.collect(Collectors.toList()); 
	}
	
}
