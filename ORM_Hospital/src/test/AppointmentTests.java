package test;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;

import Services.AppointmentsManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.*;



class AppointmentTests {

//	@Test
//	void testCreate() throws ParseException {
//	    Patient patient = new Patient("Joao Paciente1", "joaoP@gmail.com", "2323232", "3443-4434");
//	    Doctor doctor = new Doctor("Joao Doutor1", "joaoD@gmail.com", "2323232", "3443-4434", "pediatra");
//	    
//	    Appointment appointment = new Appointment("2024-11-20 16:00:00", doctor, patient);
//	    
//	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hospitalPU");
//	    EntityManager em = emf.createEntityManager();
//	    
//	    em.getTransaction().begin();
//	    
//	    patient.addAppointment(appointment);
//	    doctor.addAppointment(appointment);
//
//	    em.persist(patient);
//	    em.persist(doctor);
//
//	    em.getTransaction().commit();
//	    
//	    Appointment retrievedAppointment = em.find(Appointment.class, appointment.getId());
//	    
//	    em.close();
//	    emf.close();
//	    
//	    assertNotNull(retrievedAppointment, "The appointment should have been saved and retrieved successfully.");
//	    assertEquals(doctor.getId(), retrievedAppointment.getDoctor().getId(), "The appointment should reference the correct doctor.");
//	    assertEquals(patient.getId(), retrievedAppointment.getPatient().getId(), "The appointment should reference the correct patient.");
//	}
//	
//	@Test
//	void testRemove() throws ParseException {
//
//	    Patient patient = new Patient("Joao Paciente2", "joaoP@gmail.com", "2323232", "3443-4434");
//	    Doctor doctor = new Doctor("Joao Doutor2", "joaoD@gmail.com", "2323232", "3443-4434", "pediatra");
//
//
//	    Appointment appointment = new Appointment("2024-12-20 16:00:00", doctor, patient);
//	    
//	    patient.addAppointment(appointment);
//	    doctor.addAppointment(appointment);
//
//	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hospitalPU");
//	    EntityManager em = emf.createEntityManager();
//
//	    em.getTransaction().begin();
//
//	    em.persist(patient);
//	    em.persist(doctor);
//	    em.persist(appointment);
//	    em.getTransaction().commit();
//
//	    em.getTransaction().begin();
//	    Appointment retrievedAppointment = em.find(Appointment.class, appointment.getId());
//
//
//	    if (retrievedAppointment != null) {
//	    	patient.removeAppointment(appointment);
//		    doctor.removeAppointment(appointment);
//	        em.remove(retrievedAppointment);
//	    }
//	    em.getTransaction().commit();
//
//
//	    Appointment deletedAppointment = em.find(Appointment.class, appointment.getId());
//
//	    em.close();
//	    emf.close();
//
//
//	    assertNull(deletedAppointment, "The appointment should have been deleted.");
//	    assertFalse(doctor.getAppointments().contains(retrievedAppointment), "The appointment should be removed from the doctor's list.");
//	    assertFalse(patient.getAppointments().contains(retrievedAppointment), "The appointment should be removed from the patient's list.");
//	}
	
	@Test
	void testGetSystemAppointments() throws ParseException {
		Patient patient1 = new Patient("Maria Paciente1", "mariaP@gmail.com", "123456789", "1234-5678");
		Patient patient2 = new Patient("João Paciente2", "joaoP@gmail.com", "987654321", "4321-8765");
		
		Doctor doctor1 = new Doctor("Pedro Doutor1", "pedroD@gmail.com", "987654321", "8765-4321", "cardiologista");
		Doctor doctor2 = new Doctor("Ana Doutora2", "anaD@gmail.com", "123456789", "1234-5678", "neurologista");
		
		Appointment appointment1 = new Appointment("2024-11-21 10:00:00", doctor1, patient1);
        Appointment appointment2 = new Appointment("2024-11-22 14:00:00", doctor1, patient2);
        Appointment appointment3 = new Appointment("2024-11-23 16:00:00", doctor2, patient1);
        Appointment appointment4 = new Appointment("2024-11-24 18:00:00", doctor2, patient2);
        
        AppointmentsManager appointmentsManager = AppointmentsManager.getInstance();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hospitalPU");
	    EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(patient1);
        em.persist(patient2);
        em.persist(doctor1);
        em.persist(doctor2);
        em.persist(appointment1);
        em.persist(appointment2);
        em.persist(appointment3);
        em.persist(appointment4);
        em.getTransaction().commit();
        
        em.close();
        emf.close();

        ArrayList<Appointment> appointments = appointmentsManager.getSystemAppointments();
        
        assertEquals(4, appointments.size(), "A listagem deve retornar exatamente 4 consultas.");
        
	}

}
