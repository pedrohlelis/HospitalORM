package services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import model.Appointment;
import model.Doctor;
import model.Patient;
import repository.AppointmentRepository;

public class AppointmentsManager {
	private static AppointmentsManager instance;
	private AppointmentRepository appointmentRepository;
	
	private AppointmentsManager() {
		this.appointmentRepository = new AppointmentRepository();
	}
	
	public static AppointmentsManager getInstance() {
		if(instance == null) {
			instance = new AppointmentsManager();
			return instance;
		}
		return instance;
	}
	
	public Appointment createAppointment(String date, Doctor doctor, Patient patient) throws ParseException {
		return appointmentRepository.saveAppointment(date, doctor, patient);
	}
	
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.getAllAppointments();
    }

    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.getAppointmentById(id);
    }

    public void updateAppointment(Long id, Appointment updatedAppointment) throws ParseException {
        appointmentRepository.updateAppointment(id, updatedAppointment);
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteAppointment(id);
    }
    
    public void closeConnection() {
        appointmentRepository.close();
    }
    

	public void deleteAllAppointments() {
		appointmentRepository.deleteAllAppointments();
	}
}
