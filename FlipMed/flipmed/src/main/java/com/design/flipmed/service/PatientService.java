package com.design.flipmed.service;

import com.design.flipmed.mode.Print;
import com.design.flipmed.model.Patient;
import com.design.flipmed.repository.PatientRepository;

public class PatientService {
	PatientRepository patientRepository;
	Print print;
	
	public PatientService(Print print,PatientRepository patientRepository) {
		this.print = print; 
		this.patientRepository = patientRepository; 
	}
	
	public void registerPatient(Patient patient) {
		patientRepository.registerPatient(patient);
		print.printData(patient.getPatientName()+" registered Successfully");
	}
}
