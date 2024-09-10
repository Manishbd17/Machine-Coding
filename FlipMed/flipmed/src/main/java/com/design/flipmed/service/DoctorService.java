package com.design.flipmed.service;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.design.flipmed.mode.Print;
import com.design.flipmed.model.AvailableDoctor;
import com.design.flipmed.model.Doctor;
import com.design.flipmed.model.Specialization;
import com.design.flipmed.model.TimeSlot;
import com.design.flipmed.repository.DoctorRepository;
import com.design.flipmed.util.Utils;

public class DoctorService {
	DoctorRepository doctorRepository;
	Print print; 
	
	public DoctorService(DoctorRepository doctorRepository,Print print) {
		this.doctorRepository = doctorRepository; 
		this.print = print; 
	}
	
	public void registerDoctor(Doctor doctor) {
		doctorRepository.registerDoctor(doctor);
		print.printData("Welcome "+ doctor.getDoctorName());
	}
	
	public void addAvailability(Integer doctorId, TimeSlot timeSlot) {
		LocalTime start = Utils.convertStringToTime(timeSlot.getStart());
		LocalTime end = Utils.convertStringToTime(timeSlot.getEnd()); 
		
		if(ChronoUnit.MINUTES.between(start, end)%60==30) {
			doctorRepository.addAvailability(doctorId, timeSlot);
			print.printData("Done doc");
		} else {
			print.printData("Sorry" + doctorRepository.getDoctorDetails(doctorId).getDoctorName() + " slots are 30 mins");
		}
	}
	
	public void showAvailableSlotsBySpeciality(Specialization specialization) {
		List<Doctor> doctorsBySpeciality‎ = doctorRepository.getDoctorsBySpeciality(specialization);
		List<AvailableDoctor> availableTimeSlots‎ = doctorRepository.getAvailableTimeForAllDoctorsBySpeciality(doctorsBySpeciality‎);
		if(availableTimeSlots‎.size()>0) {
			print.printData("Available Doctors with time slots");
			for(AvailableDoctor doctor : availableTimeSlots‎) {
				for(TimeSlot slot: doctor.getSlotList()) {
					print.printData(doctor.getDoctor().getDoctorName() + "" + slot.getStart() + "-"+ slot.getEnd());
				}
			}
		} else {
			print.printData("No slots available");
		}
	}
	
}
