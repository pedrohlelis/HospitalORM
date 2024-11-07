package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Appointment;

public class AppointmentRepository {
	private EntityManagerFactory emf;
	private EntityManager em;

    public AppointmentRepository() {
        this.emf = Persistence.createEntityManagerFactory("hospitalPU");
        this.em = emf.createEntityManager();
    }

    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = em.createQuery("SELECT a FROM Appointment a", Appointment.class)
                                           .getResultList();
        return appointments;
    }

    public Appointment getAppointmentById(Long id) {
        Appointment appointment = em.find(Appointment.class, id);
        return appointment;
    }

    public void updateAppointment(Long id, Appointment updatedAppointment) {
        em.getTransaction().begin();
        Appointment existingAppointment = em.find(Appointment.class, id);
        if (existingAppointment != null) {
            existingAppointment.setAppointmentDate(updatedAppointment.getAppointmentDate());
            existingAppointment.setDoctor(updatedAppointment.getDoctor());
            existingAppointment.setPatient(updatedAppointment.getPatient());
            em.merge(existingAppointment);
        }
        em.getTransaction().commit();
    }

    public void deleteAppointment(Long id) {
        em.getTransaction().begin();
        Appointment appointment = em.find(Appointment.class, id);
        if (appointment != null) {
            em.remove(appointment);
        }
        em.getTransaction().commit();
    }
    
    public void close() {
    	em.close();
    	emf.close();
    }
    
    public void open() {
    	em = emf.createEntityManager();
    }
}
