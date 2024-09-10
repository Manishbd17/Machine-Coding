package com.design.flipmed.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.design.flipmed.exceptions.DoctorAlreadyPresentException;
import com.design.flipmed.exceptions.DoctorNotPresentException;
import com.design.flipmed.exceptions.SlotException;
import com.design.flipmed.model.AvailableDoctor;
import com.design.flipmed.model.Doctor;
import com.design.flipmed.model.Specialization;
import com.design.flipmed.model.TimeSlot;

public class DoctorRepository {
	Map<Integer,Doctor> doctors = new HashMap<>(); 
	Map<Specialization,List<Doctor>> doctorsBySpeciality = new HashMap<>();	
	
	//registerDoctor
	//addAvailability
	//getDoctorsBySpeciality
	//getAvailableTimeForAllDoctorsBySpeciality
	//isDoctorRegistered
	//getDoctorDetails
	//freeSlot
	
	public void registerDoctor(Doctor doctor) {
		if(doctors.containsKey(doctor.getDoctorId())) {
			throw new DoctorAlreadyPresentException();
		}
		doctors.put(doctor.getDoctorId(), doctor);
		if(!doctorsBySpeciality.containsKey(doctor.getSpecialization())) {
			doctorsBySpeciality.put(doctor.getSpecialization(), new ArrayList<>()); 
		}
		doctorsBySpeciality.get(doctor.getSpecialization()).add(doctor); 
	}
	
	public void addAvailability(Integer doctorId,TimeSlot timeSlot) {
		if(!doctors.containsKey(doctorId)) {
			throw new DoctorNotPresentException();
		}
		Doctor doctor = doctors.get(doctorId); 
		HashMap<TimeSlot,Boolean> slots = doctor.getSlots(); 
		slots.put(timeSlot, true); 
	} 
	
	public List<Doctor> getDoctorsBySpeciality(Specialization specialization) {
		if(!doctorsBySpeciality.containsKey(specialization)) {
			throw new DoctorNotPresentException();
		}
		return doctorsBySpeciality.get(specialization); 
	}
	
	
	public List<AvailableDoctor> getAvailableTimeForAllDoctorsBySpeciality(List<Doctor> specializedDoctors)  {
		List<AvailableDoctor> doctorsWithAvailableSlots = new ArrayList<>(); 
		for(Doctor doctor: specializedDoctors) {
			AvailableDoctor availableDoctor = new AvailableDoctor(); 
			availableDoctor.setDoctor(doctor);
			List<TimeSlot> slot = new ArrayList<>();
			HashMap<TimeSlot,Boolean> origSlots = doctor.getSlots(); 
			for(Map.Entry<TimeSlot, Boolean> entry: origSlots.entrySet()) {
				if(entry.getValue()) {
					slot.add(entry.getKey()); 
				}
			}
			availableDoctor.setSlotList(slot);
			doctorsWithAvailableSlots.add(availableDoctor); 
		}
		return doctorsWithAvailableSlots;
	}
	
	public boolean isDoctorRegistered(Integer doctorId) {
		return doctors.containsKey(doctorId);
	}
	
	public Doctor getDoctorDetails(Integer doctorId) {
		return doctors.get(doctorId); 
	}
	
	
	public void freeSlot(Integer doctorId,TimeSlot timeSlot) {
		Boolean flag = doctors.get(doctorId).getSlots().put(timeSlot, true); 
		if(flag == null) {
			doctors.get(doctorId).getSlots().remove(timeSlot); 
			throw new SlotException("Slot Not Found");
		}
	}
	
	
	
}
