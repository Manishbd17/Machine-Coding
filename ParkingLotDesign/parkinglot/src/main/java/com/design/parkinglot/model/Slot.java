package com.design.parkinglot.model;

public class Slot {
	
	private Car parkedCar; 
	private Integer slotNumber; 
	
	public Slot(final Integer slotNumber) {
		this.slotNumber = slotNumber; 
	}
	
	public Car getParkedCar() {
		return parkedCar;
	}
	
	public Integer getSlotNumber() {
		return slotNumber;
	}
	
	public boolean isSlotFree() {
		return parkedCar == null; 
	}
	
	public void assignCar(Car car) {
		this.parkedCar = car; 
	}
	
	public void unAssignCar() {
		this.parkedCar = null; 
	}
	
	
}
