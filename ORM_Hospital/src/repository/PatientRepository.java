package repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Patient;

public class PatientRepository {
    private EntityManagerFactory emf;
    private EntityManager em;

    public PatientRepository() {
        this.emf = Persistence.createEntityManagerFactory("hospitalPU");
        this.em = emf.createEntityManager();
    }

    // Save a patient
    public void savePatient(Patient patient) {
        em.getTransaction().begin();
        em.persist(patient);
        em.getTransaction().commit();
    }

    // Get all patients
    public List<Patient> getAllPatients() {
        List<Patient> patients = em.createQuery("SELECT p FROM Patient p", Patient.class).getResultList();
        em.close();
        return patients;
    }

    // Get a patient by ID
    public Patient getPatientById(Long id) {
        Patient patient = em.find(Patient.class, id);
        return patient;
    }

    // Update a patient
    public void updatePatient(Long id, Patient updatedPatient) {
        em.getTransaction().begin();
        Patient existingPatient = em.find(Patient.class, id);
        if (existingPatient != null) {
            // Update properties
            existingPatient.setName(updatedPatient.getName());
            existingPatient.setEmail(updatedPatient.getEmail());
            existingPatient.setCpf(updatedPatient.getCpf());
            existingPatient.setPhone(updatedPatient.getPhone());
            em.merge(existingPatient);
        }
        em.getTransaction().commit();
        //em.close();
    }

    // Delete a patient
    public void deletePatient(Long id) {
        em.getTransaction().begin();
        Patient patient = em.find(Patient.class, id);
        if (patient != null) {
            em.remove(patient);
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

