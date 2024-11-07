package Services;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import model.Appointment;

public class AppointmentsManager {
	private ArrayList<Appointment> systemAppointments;
	private static AppointmentsManager instance;
	private EntityManagerFactory emf;
	
	private AppointmentsManager() {
		this.emf = Persistence.createEntityManagerFactory("hospitalPU");
		this.systemAppointments = new ArrayList<>();
	}
	
	public static AppointmentsManager getInstance() {
		if(instance == null) {
			instance = new AppointmentsManager();
			return instance;
		}
		return instance;
	}
	
	
	public ArrayList<Appointment> getSystemAppointments(){
		updateSystemAppointments();
		return this.systemAppointments;
	}
	
	private boolean updateSystemAppointments() {
		EntityManager em = null;
        try {
        	em = emf.createEntityManager();
            em.getTransaction().begin();
            
            List<Appointment> appointmentsFromDb = em
                    .createQuery("SELECT a FROM Appointment a", Appointment.class)
                    .getResultList();
            
            systemAppointments.clear();
            systemAppointments.addAll(appointmentsFromDb);

            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
            	em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            if (em != null) {
            	em.close();
            }
        }
	}
}
