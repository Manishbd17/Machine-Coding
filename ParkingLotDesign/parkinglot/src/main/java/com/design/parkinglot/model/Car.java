package com.design.parkinglot.model;

public class Car {
	
	private String color; 
	private String registrationNumber;
	
	
	public String getColor() {
		return color;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	
	public Car(final String color,final String registrationNumber) {
		this.color = color; 
		this.registrationNumber = registrationNumber; 
	}
	
	
}
