package services;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import model.Appointment;
import model.Patient;
import repository.PatientRepository;

public class PatientsManager {
	private static PatientsManager instance;
	private PatientRepository patientRepository;
	
	private PatientsManager() {
		this.patientRepository = new PatientRepository();
	}
	
	public static PatientsManager getInstance() {
		if(instance == null) {
			instance = new PatientsManager();
			return instance;
		}
		return instance;
	}
	
	public void savePatient(Patient patient) {
        patientRepository.savePatient(patient);
    }
	
    public void closeConnection() {
        patientRepository.close();
    }
    
    public void openConnection() {
        patientRepository.open();
    }

}
