package repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Appointment;
import model.Doctor;

public class DoctorRepository {
    private EntityManagerFactory emf;
    private EntityManager em;

    public DoctorRepository() {
        this.emf = Persistence.createEntityManagerFactory("hospitalPU");
        this.em = emf.createEntityManager();
    }


    public boolean saveDoctor(Doctor patient) {
    	try {
    		em.getTransaction().begin();
            em.persist(patient);
            em.getTransaction().commit();
            return true;
    	}
        catch(Exception e) {
        	return false;
        }
    }


    public List<Doctor> getAllPatients() {
        List<Doctor> doctors = em.createQuery("SELECT d FROM Doctor d", Doctor.class).getResultList();
        return doctors;
    }


    public Doctor getPatientById(Long id) {
        Doctor doctor = em.find(Doctor.class, id);
        return doctor;
    }


    public boolean updateDoctor(Long id, Doctor updatedDoctor) {
    	try {
	        em.getTransaction().begin();
	        Doctor existingDoctor = em.find(Doctor.class, id);
	        if (existingDoctor != null) {
	            // Update properties
	            existingDoctor.setName(updatedDoctor.getName());
	            existingDoctor.setEspecializacao(updatedDoctor.getEspecializacao());
	            existingDoctor.setEmail(updatedDoctor.getEmail());
	            existingDoctor.setCpf(updatedDoctor.getCpf());
	            existingDoctor.setPhone(updatedDoctor.getPhone());
	            em.merge(existingDoctor);
	        }
	        em.getTransaction().commit();
	        return true;
    	}catch(Exception e) {
    		return false;
    	}
    }


    public void deletePatient(Long id) {
        em.getTransaction().begin();
        Doctor doctor = em.find(Doctor.class, id);
        if (doctor != null) {
            em.remove(doctor);
        }
        em.getTransaction().commit();
    }
    
    public void close() {
    	em.close();
    }
    
    public void open() {
    	em = emf.createEntityManager();
    }
}

