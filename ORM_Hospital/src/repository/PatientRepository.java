package repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Patient;

public class PatientRepository {
    private EntityManagerFactory emf;

    public PatientRepository() {
    	this.emf = Persistence.createEntityManagerFactory("hospitalPU");
    }

    public void savePatient(Patient patient) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(patient);
        em.getTransaction().commit();
        em.close();
    }

    public List<Patient> getAllPatients() {
        EntityManager em = emf.createEntityManager();
        List<Patient> patients = em.createQuery("SELECT p FROM Patient p", Patient.class).getResultList();
        em.close();
        return patients;
    }

    public Patient getPatientById(Long id) {
        EntityManager em = emf.createEntityManager();
        Patient patient = em.find(Patient.class, id);
        em.close();
        return patient;
    }

    public void updatePatient(Long id, Patient updatedPatient) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Patient existingPatient = em.find(Patient.class, id);
        if (existingPatient != null) {
            existingPatient.setName(updatedPatient.getName());
            existingPatient.setEmail(updatedPatient.getEmail());
            existingPatient.setCpf(updatedPatient.getCpf());
            existingPatient.setPhone(updatedPatient.getPhone());
            em.merge(existingPatient);
        }
        em.getTransaction().commit();
        em.close();
    }

    public void deletePatient(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Patient patient = em.find(Patient.class, id);
        if (patient != null) {
            em.remove(patient);
        }
        em.getTransaction().commit();
        em.close();
    }

    public void deleteAllPatients() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Patient").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
    
    public void close() {
		if (emf != null && emf.isOpen()) {
	        emf.close();
	    }
    }
    
}

