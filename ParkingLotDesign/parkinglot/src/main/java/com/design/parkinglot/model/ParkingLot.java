package com.design.parkinglot.model;

import java.util.HashMap;
import java.util.Map;

import com.design.parkinglot.exception.InvalidSlotException;
import com.design.parkinglot.exception.NoFreeSlotException;
import com.design.parkinglot.exception.ParkingLotException;

public class ParkingLot {

	private static int MAX_CAPACITY=10000; 
	private int capacity; 
	private Map<Integer,Slot> slots; 
	
	public int getCapacity() {
		return capacity; 
	}
	
	public Map<Integer,Slot> getSlots() {
		return slots; 
	}
	
	public ParkingLot(final int capacity) {
		if(capacity<=0 || capacity>MAX_CAPACITY) {
			throw new ParkingLotException("Invalid capacity given for parking lot.");
		}
		this.capacity = capacity; 
		this.slots = new HashMap<>(); 
	}
	
	public Slot getSlot(final Integer slotNumber) {
		if(slotNumber> getCapacity() || slotNumber<=0) {
			throw new InvalidSlotException(); 
		}
		final Map<Integer,Slot> allSlots = getSlots(); 
		if(!allSlots.containsKey(slotNumber)) {
			allSlots.put(slotNumber, new Slot(slotNumber)); 
		}
		return allSlots.get(slotNumber); 		
	}
	
	public Slot park(final Car car, final Integer slotNumber) {
		Slot slot = getSlot(slotNumber); 
		if(!slot.isSlotFree()) {
			throw new NoFreeSlotException(); 
		}
		slot.assignCar(car);
		return slot; 
	}
	
	public Slot makeSlotFree(final Integer slotNumber) {
		Slot slot = getSlot(slotNumber); 
		slot.unAssignCar();
		return slot; 
	}
	
	
	
}
