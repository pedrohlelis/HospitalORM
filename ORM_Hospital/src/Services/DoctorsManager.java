package services;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import model.Appointment;
import model.Doctor;
import model.Patient;
import repository.DoctorRepository;
import repository.PatientRepository;

public class DoctorsManager {
	private static DoctorsManager instance;
	private DoctorRepository doctorRepository;
	
	private DoctorsManager() {
		this.doctorRepository = new DoctorRepository();
	}
	
	public static DoctorsManager getInstance() {
		if(instance == null) {
			instance = new DoctorsManager();
			return instance;
		}
		return instance;
	}
	
	public void saveDoctor(Doctor doctor) {
        doctorRepository.saveDoctor(doctor);
    }
	
    public void openConnection() {
        doctorRepository.open();
    }
	
    public void closeConnection() {
        doctorRepository.close();
    }

}
