package com.design.flipmed.model;

import java.util.HashMap;
import java.util.List;

public class Patient {
	Integer patientId; 
	String patientName; 
	HashMap<Doctor,List<TimeSlot>> bookedSlots; 
	
	public Patient(Integer patientId,String patientName) {
		this.patientId = patientId;
		this.patientName = patientName;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public HashMap<Doctor, List<TimeSlot>> getBookedSlots() {
		return bookedSlots;
	}

	public void setBookedSlots(HashMap<Doctor, List<TimeSlot>> bookedSlots) {
		this.bookedSlots = bookedSlots;
	}
	
	
	
}
