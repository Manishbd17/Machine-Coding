package com.design.flipmed.model;

public class Booking {
	Integer bookingId;
	Doctor doctor; 
	Patient patient; 
	TimeSlot slot; 
	Boolean waitlist; 
	
	
	public Booking (Integer bookingId,Doctor doctor,Patient patient,TimeSlot slot) {
		this.bookingId = bookingId;
		this.doctor = doctor; 
		this.patient = patient; 
		this.slot = slot; 
		this.waitlist = false; 
	}


	public Integer getBookingId() {
		return bookingId;
	}


	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}


	public Doctor getDoctor() {
		return doctor;
	}


	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}


	public Patient getPatient() {
		return patient;
	}


	public void setPatient(Patient patient) {
		this.patient = patient;
	}


	public TimeSlot getSlot() {
		return slot;
	}


	public void setSlot(TimeSlot slot) {
		this.slot = slot;
	}


	public Boolean getWaitlist() {
		return waitlist;
	}


	public void setWaitlist(Boolean waitlist) {
		this.waitlist = waitlist;
	}
	
	
	
}
