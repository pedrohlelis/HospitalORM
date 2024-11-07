package test;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import services.AppointmentsManager;
import services.DoctorsManager;
import services.PatientsManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.*;



class AppointmentTests {
	private AppointmentsManager am = AppointmentsManager.getInstance();
	private PatientsManager pm = PatientsManager.getInstance();
	private DoctorsManager dm = DoctorsManager.getInstance();
	
	@Test
	void testCreate() throws ParseException {
	    Patient patient = new Patient("Joao Paciente123", "joaoP@gmail.com", "2323232", "3443-4434");
	    Doctor doctor = new Doctor("Joao Doutor123", "joaoD@gmail.com", "2323232", "3443-4434", "pediatra");
	    
	    pm.savePatient(patient);
	    dm.saveDoctor(doctor);
	    Appointment appointment = am.createAppointment("2024-11-20 16:00:03", doctor, patient);
	    
	    Appointment retrievedAppointment = am.getAppointmentById(appointment.getId());
	    
	    assertNotNull(retrievedAppointment, "The appointment should have been saved and retrieved successfully.");
	    assertEquals(doctor.getId(), retrievedAppointment.getDoctor().getId(), "The appointment should reference the correct doctor.");
	    assertEquals(patient.getId(), retrievedAppointment.getPatient().getId(), "The appointment should reference the correct patient.");
	}

	@Test
	void testRemove() throws ParseException {
		
	    Patient patient = new Patient("Joao Paciente2", "joaoP@gmail.com", "2323232", "3443-4434");
	    Doctor doctor = new Doctor("Joao Doutor2", "joaoD@gmail.com", "2323232", "3443-4434", "pediatra");	    
	    
	    pm.savePatient(patient);
	    dm.saveDoctor(doctor);

	    Appointment appointment = am.createAppointment("2024-11-21 10:00:00", doctor, patient);
	    
	    Appointment retrievedAppointment = am.getAppointmentById(appointment.getId());


	    if (retrievedAppointment != null) {
	    	patient.removeAppointment(appointment);
		    doctor.removeAppointment(appointment);
	        am.deleteAppointment(retrievedAppointment.getId());
	    }


	    Appointment deletedAppointment = am.getAppointmentById(appointment.getId());
	    
	    assertNull(deletedAppointment, "The appointment should have been deleted.");
	    assertFalse(doctor.getAppointments().contains(retrievedAppointment), "The appointment should be removed from the doctor's list.");
	    assertFalse(patient.getAppointments().contains(retrievedAppointment), "The appointment should be removed from the patient's list.");
	}
	
	@Test
	void testGetSystemAppointments() throws ParseException {
		
		Patient patient1 = new Patient("Maria Paciente1", "mariaP@gmail.com", "123456789", "1234-5678");
		Patient patient2 = new Patient("João Paciente2", "joaoP@gmail.com", "987654321", "4321-8765");
		
		Doctor doctor1 = new Doctor("Pedro Doutor1", "pedroD@gmail.com", "987654321", "8765-4321", "cardiologista");
		Doctor doctor2 = new Doctor("Ana Doutora2", "anaD@gmail.com", "123456789", "1234-5678", "neurologista");
		
        pm.savePatient(patient1);
        pm.savePatient(patient2);
        dm.saveDoctor(doctor1);
        dm.saveDoctor(doctor2);
		
        am.createAppointment("2024-11-20 16:00:03", doctor1, patient1);
        
		am.createAppointment("2024-11-21 10:00:00", doctor1, patient1);
        am.createAppointment("2024-11-22 14:00:00", doctor1, patient2);
        am.createAppointment("2024-11-23 16:00:00", doctor2, patient1);
        am.createAppointment("2024-11-24 18:00:00", doctor2, patient2);
        
        List<Appointment> appointments = am.getAllAppointments();
        
        
        assertEquals(5, appointments.size(), "A listagem deve retornar exatamente 4 consultas.");
        
	}
	
//	@BeforeEach
//	void setUp() {
//	    am = AppointmentsManager.getInstance();
//	    pm = PatientsManager.getInstance();
//	    dm = DoctorsManager.getInstance();
//	}
	
//	@AfterEach
//	void tearDown() {
//	    am.deleteAllAppointments();
//	    pm.deleteAllPatients();
//	    dm.deleteAllDoctors();
//	}

}
