package test;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.Date;

import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.*;



class AppointmentTests {

	@Test
	void testCreate() throws ParseException {
	    Patient patient = new Patient("Joao Paciente1", "joaoP@gmail.com", "2323232", "3443-4434");
	    Doctor doctor = new Doctor("Joao Doutor1", "joaoD@gmail.com", "2323232", "3443-4434", "pediatra");
	    
	    Appointment appointment = new Appointment("2024-11-20 16:00:00", doctor, patient);
	    
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hospitalPU");
	    EntityManager em = emf.createEntityManager();
	    
	    em.getTransaction().begin();
	    
	    patient.addAppointment(appointment);
	    doctor.addAppointment(appointment);

	    em.persist(patient);
	    em.persist(doctor);

	    em.getTransaction().commit();
	    
	    Appointment retrievedAppointment = em.find(Appointment.class, appointment.getId());
	    
	    em.close();
	    emf.close();
	    
	    assertNotNull(retrievedAppointment, "The appointment should have been saved and retrieved successfully.");
	    assertEquals(doctor.getId(), retrievedAppointment.getDoctor().getId(), "The appointment should reference the correct doctor.");
	    assertEquals(patient.getId(), retrievedAppointment.getPatient().getId(), "The appointment should reference the correct patient.");
	}
	
	@Test
	void testRemove() throws ParseException {

	    Patient patient = new Patient("Joao Paciente2", "joaoP@gmail.com", "2323232", "3443-4434");
	    Doctor doctor = new Doctor("Joao Doutor2", "joaoD@gmail.com", "2323232", "3443-4434", "pediatra");


	    Appointment appointment = new Appointment("2024-12-20 16:00:00", doctor, patient);
	    
	    patient.addAppointment(appointment);
	    doctor.addAppointment(appointment);

	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hospitalPU");
	    EntityManager em = emf.createEntityManager();

	    em.getTransaction().begin();

	    em.persist(patient);
	    em.persist(doctor);
	    em.persist(appointment);
	    em.getTransaction().commit();

	    em.getTransaction().begin();
	    Appointment retrievedAppointment = em.find(Appointment.class, appointment.getId());


	    if (retrievedAppointment != null) {
	    	patient.removeAppointment(appointment);
		    doctor.removeAppointment(appointment);
	        em.remove(retrievedAppointment);
	    }
	    em.getTransaction().commit();


	    Appointment deletedAppointment = em.find(Appointment.class, appointment.getId());

	    em.close();
	    emf.close();


	    assertNull(deletedAppointment, "The appointment should have been deleted.");
	    assertFalse(doctor.getAppointments().contains(retrievedAppointment), "The appointment should be removed from the doctor's list.");
	    assertFalse(patient.getAppointments().contains(retrievedAppointment), "The appointment should be removed from the patient's list.");
	}

}
