package com.design.flipmed.model;

import java.util.HashMap;

public class Doctor {
	Integer doctorId; 
	String doctorName; 
	Specialization specialization; 
	HashMap<TimeSlot,Boolean> slots; 
	Integer rating; 
	
	public Doctor(Integer doctorId,String doctorName,Specialization specialization,HashMap<TimeSlot,Boolean> slots) {
		this.doctorId = doctorId; 
		this.doctorName = doctorName;
		this.specialization = specialization; 
		this.slots = slots; 
	}
	
	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public Specialization getSpecialization() {
		return specialization;
	}

	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}

	public HashMap<TimeSlot, Boolean> getSlots() {
		return slots;
	}

	public void setSlots(HashMap<TimeSlot, Boolean> slots) {
		this.slots = slots;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
	
}
