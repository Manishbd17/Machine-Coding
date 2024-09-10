package com.design.flipmed.repository;

import java.util.HashMap;
import java.util.Map;

import com.design.flipmed.exceptions.PatientAlreadyPresentException;
import com.design.flipmed.model.Patient;

public class PatientRepository {
	Map<Integer,Patient> patients = new HashMap<>();
	
	public void registerPatient(Patient patient) {
		if(patients.containsKey(patient.getPatientId())) {
			throw new PatientAlreadyPresentException(); 
		}
		patients.put(patient.getPatientId(), patient); 
	}
	
	public boolean isPatientRegistered(Integer patientId) {
		return patients.containsKey(patientId); 
	}
	
}
