package com.design.flipmed.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.design.flipmed.exceptions.BookingNotPresentException;
import com.design.flipmed.exceptions.DoctorNotPresentException;
import com.design.flipmed.exceptions.PatientAlreadyPresentException;
import com.design.flipmed.exceptions.PatientNotPresentException;
import com.design.flipmed.mode.Print;
import com.design.flipmed.model.Booking;
import com.design.flipmed.model.Doctor;
import com.design.flipmed.model.Patient;
import com.design.flipmed.model.TimeSlot;
import com.design.flipmed.repository.DoctorRepository;
import com.design.flipmed.repository.PatientRepository;

public class BookingService {
	DoctorRepository doctorRepository;
	PatientRepository patientRepository; 
	Print print; 
	HashMap<Integer,Booking> bookings = new HashMap<>(); 
	Map<Integer,List<TimeSlot>> patientSlots = new HashMap<>(); 
	Queue<Booking> waitListQueue = new LinkedList<>(); 
	
	static int index = 1; 
	
	public BookingService(DoctorRepository doctorRepository,PatientRepository patientRepository,Print print) {
		this.doctorRepository = doctorRepository; 
		this.patientRepository = patientRepository; 
		this.print = print; 
	}
	
	//book appointment
	//cancel appointment
	//showBookings
	//checkFreeSlots
	
	public void bookAppointment(Patient patient,Doctor doctor,String fromSlot) {
		if(!patientRepository.isPatientRegistered(patient.getPatientId())) {
			throw new PatientNotPresentException();
		}
		if(!doctorRepository.isDoctorRegistered(doctor.getDoctorId())) {
			throw new DoctorNotPresentException();
		}
		if(patientSlots.containsKey(patient.getPatientId())) {
			for(TimeSlot slot : patientSlots.get(patient.getPatientId())) {
				if(slot.getStart().equals(fromSlot)) {
					throw new PatientAlreadyPresentException();
				}
			}
		}
		else {
			patientSlots.put(patient.getPatientId(), new ArrayList<>()); 
		}
		
		Doctor doctorDetails = doctorRepository.getDoctorDetails(doctor.getDoctorId()); 
		HashMap<TimeSlot,Boolean> slots = doctorDetails.getSlots(); 
		for(Map.Entry<TimeSlot, Boolean> slot: slots.entrySet()) {
			if(slot.getKey().getStart().equals(fromSlot) && slot.getValue()) {
				slots.put(slot.getKey(), false); 
				patientSlots.get(patient.getPatientId()).add(slot.getKey()); 
				Booking booking = new Booking(index++,doctor,patient,slot.getKey());
				bookings.put(booking.getBookingId(), booking); 
				print.printData("Appointment Booked Successfully " + booking.getBookingId());
				return ; 
			}
		}
		print.printData("No available slots");
		Booking booking = new Booking(index++,doctor,patient,new TimeSlot(fromSlot,fromSlot)); 
		booking.setWaitlist(true);
		print.printData("Added to the waitlist. Booking ID: "+booking.getBookingId());
		waitListQueue.add(booking); 	
	}
	
	
	public void cancelBooking(Integer bookingId) {
		if(!bookings.containsKey(bookingId)) {
			throw new BookingNotPresentException();
		}
		Booking booking = bookings.get(bookingId); 
		doctorRepository.freeSlot(booking.getDoctor().getDoctorId(), booking.getSlot());
		bookings.remove(bookingId); 
		print.printData("Booking Cancelled");
		patientSlots.get(booking.getPatient().getPatientId()).remove(booking.getSlot()); 
		checkForFreeSlot(booking); 
	}
	
	public void showBookedAppointments() {
		for(Map.Entry<Integer, Booking> bookingEntry : bookings.entrySet()) {
			Booking booking = bookingEntry.getValue(); 
			print.printData(
					booking.getBookingId()+" "+
					booking.getDoctor().getDoctorName()+" "+
					booking.getPatient().getPatientName()+" "+
					booking.getSlot().getStart()+"-"+booking.getSlot().getEnd());
		}
	}

	public void checkForFreeSlot(Booking booking) {
		for(Booking waitList: waitListQueue) {
			if(waitList.getSlot().getStart().equals(booking.getSlot().getStart())) {
				waitList.getSlot().setEnd(booking.getSlot().getEnd());
				waitList.setWaitlist(false);
				Doctor doctorDetails = doctorRepository.getDoctorDetails(booking.getDoctor().getDoctorId()); 
				HashMap<TimeSlot,Boolean> slots = doctorDetails.getSlots(); 
				for(Map.Entry<TimeSlot, Boolean> slot: slots.entrySet()) {
					if(slot.getKey().getStart().equals(booking.getSlot().getStart())) {
						slots.put(slot.getKey(),false); 
						break; 
					}
				}
				bookings.put(waitList.getBookingId(), waitList); 
				waitListQueue.remove(waitList); 
				return ;
			}
		}
	}	
	
}
