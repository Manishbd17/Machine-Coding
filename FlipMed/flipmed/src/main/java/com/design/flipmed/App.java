package com.design.flipmed;

import java.util.HashMap;

import com.design.flipmed.mode.ConsolePrint;
import com.design.flipmed.mode.Print;
import com.design.flipmed.model.Doctor;
import com.design.flipmed.model.Patient;
import com.design.flipmed.model.Specialization;
import com.design.flipmed.model.TimeSlot;
import com.design.flipmed.repository.DoctorRepository;
import com.design.flipmed.repository.PatientRepository;
import com.design.flipmed.service.BookingService;
import com.design.flipmed.service.DoctorService;
import com.design.flipmed.service.PatientService;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        Print print = new ConsolePrint();
        DoctorRepository doctorsRepository = new DoctorRepository();
        PatientRepository patientsRepository = new PatientRepository();
        DoctorService doctorService = new DoctorService(doctorsRepository, print);
        PatientService patientService = new PatientService(print,patientsRepository);
        BookingService bookingService = new BookingService(doctorsRepository, patientsRepository,print);

        // Doctors
        Doctor doctorCurious = new Doctor(1, "curious", Specialization.CARDIOLOGIST,new HashMap<>());
        Doctor doctorDreadful = new Doctor(2, "Dreadful", Specialization.DERMATOLOGIST,new HashMap<>());
        Doctor doctorDaring = new Doctor(3, "Daring", Specialization.DERMATOLOGIST,new HashMap<>());

        // Patients
        Patient patientA = new Patient(1, "PatientA");
        Patient patientB = new Patient(2, "PatientB");
        Patient patientC = new Patient(3, "PatientC");


        // Registering doctor
        doctorService.registerDoctor(doctorCurious);
        doctorService.registerDoctor(doctorDreadful);
        // Add invalid Availability
        doctorService.addAvailability(doctorCurious.getDoctorId(), new TimeSlot("9:30","10:30"));
        // Add Valid Availabilities 9:30-10:00, 12:30-13:00, 16:00-16:30
        doctorService.addAvailability(doctorCurious.getDoctorId(), new TimeSlot("9:30","10:00"));
        doctorService.addAvailability(doctorCurious.getDoctorId(), new TimeSlot("12:30","13:00"));
        doctorService.addAvailability(doctorCurious.getDoctorId(), new TimeSlot("16:00","16:30"));
        doctorService.addAvailability(doctorDreadful.getDoctorId(), new TimeSlot("12:30","13:00"));
        doctorService.addAvailability(doctorDreadful.getDoctorId(), new TimeSlot("13:07","13:37"));

        doctorService.showAvailableSlotsBySpeciality(Specialization.CARDIOLOGIST);

        patientService.registerPatient(patientA);
        patientService.registerPatient(patientB);
        patientService.registerPatient(patientC);

        bookingService.bookAppointment(patientA,doctorCurious,"12:30");

        doctorService.showAvailableSlotsBySpeciality(Specialization.CARDIOLOGIST);

        bookingService.bookAppointment(patientB,doctorCurious,"12:30");
        bookingService.bookAppointment(patientC,doctorCurious,"12:30");

        bookingService.showBookedAppointments();

        bookingService.cancelBooking(1);

        doctorService.showAvailableSlotsBySpeciality(Specialization.CARDIOLOGIST);

        bookingService.showBookedAppointments();

        bookingService.cancelBooking(2);

        bookingService.showBookedAppointments();

        bookingService.bookAppointment(patientC,doctorDreadful,"13:07");

       // bookingService.bookAppointment(patientC,doctorCurious,"13:07");

        doctorService.showAvailableSlotsBySpeciality(Specialization.DERMATOLOGIST);

        bookingService.cancelBooking(1);
    }
}
