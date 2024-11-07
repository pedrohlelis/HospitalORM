package repository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Appointment;
import model.Doctor;
import model.Patient;

public class AppointmentRepository {
	private EntityManagerFactory emf;
	private DateFormat dateFormatter =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public AppointmentRepository() {
    	this.emf = Persistence.createEntityManagerFactory("hospitalPU");
    }
    
    public Appointment saveAppointment(String date, Doctor doctor, Patient patient) throws ParseException {
    	Appointment appointment = new Appointment(date, doctor, patient);
//    	patient.addAppointment(appointment);
//	    doctor.addAppointment(appointment);
    	
    	EntityManager em = emf.createEntityManager();

    	em.getTransaction().begin();
        em.persist(appointment);
        em.getTransaction().commit();
        
        em.close();
        
        return appointment;

    }

    public List<Appointment> getAllAppointments() {
    	EntityManager em = emf.createEntityManager();
        List<Appointment> appointments = em.createQuery("SELECT a FROM Appointment a", Appointment.class)
                                           .getResultList();
        em.close();
        return appointments;
    }

    public Appointment getAppointmentById(Long id) {
    	EntityManager em = emf.createEntityManager();
        Appointment appointment = em.find(Appointment.class, id);
        em.close();
        return appointment;
    }

    public void updateAppointment(Long id, Appointment updatedAppointment) throws ParseException {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        Appointment existingAppointment = em.find(Appointment.class, id);
        if (existingAppointment != null) {
            existingAppointment.setAppointmentDate(updatedAppointment.getAppointmentDate());
            existingAppointment.setDoctor(updatedAppointment.getDoctor());
            existingAppointment.setPatient(updatedAppointment.getPatient());
            em.merge(existingAppointment);
        }
        
        em.getTransaction().commit();
        em.close();
    }


    public void deleteAppointment(Long id) {
    	EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Appointment appointment = em.find(Appointment.class, id);
        if (appointment != null) {
            em.remove(appointment);
        }
        em.getTransaction().commit();
        em.close();
    }
    
    public void deleteAllAppointments() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Appointment").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
    
    public void close() {
    	emf.close();
    }
    
}
