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
	
	public Doctor getDoctorById(Long id) {
		return doctorRepository.getDoctorById(id);
	}
	
	public boolean saveDoctor(Doctor doctor) {
        if(doctorRepository.saveDoctor(doctor)) {
        	return true;
        }
        return false;
    }
	
	public boolean updateDoctor(long id, Doctor doctor) {
        if(doctorRepository.updateDoctor(id, doctor)) {
        	return true;
        }
        return false;
    }
	
	public void deleteDoctorById(long id) {
		doctorRepository.deleteDoctor(id);
	}
	
	public List<Doctor> getAllDoctors() {
		return doctorRepository.getAllDoctors();
	}
	
    public void closeConnection() {
        doctorRepository.close();
    }

	public void deleteAllDoctors() {
		doctorRepository.deleteAllDoctors();
	}

}
