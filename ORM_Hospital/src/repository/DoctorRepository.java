package repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Appointment;
import model.Doctor;

public class DoctorRepository {
    private EntityManagerFactory emf;

    public DoctorRepository() {
        this.emf = Persistence.createEntityManagerFactory("hospitalPU");
    }

    // Save a doctor
    public boolean saveDoctor(Doctor doctor) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(doctor);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }

    // Get all doctors
    public List<Doctor> getAllDoctors() {
        EntityManager em = emf.createEntityManager();
        List<Doctor> doctors = em.createQuery("SELECT d FROM Doctor d", Doctor.class).getResultList();
        em.close();
        return doctors;
    }

    // Get a doctor by ID
    public Doctor getDoctorById(Long id) {
        EntityManager em = emf.createEntityManager();
        Doctor doctor = em.find(Doctor.class, id);
        em.close();
        return doctor;
    }


    public boolean updateDoctor(Long id, Doctor updatedDoctor) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Doctor existingDoctor = em.find(Doctor.class, id);
            if (existingDoctor != null) {
                existingDoctor.setName(updatedDoctor.getName());
                existingDoctor.setEspecializacao(updatedDoctor.getEspecializacao());
                existingDoctor.setEmail(updatedDoctor.getEmail());
                existingDoctor.setCpf(updatedDoctor.getCpf());
                existingDoctor.setPhone(updatedDoctor.getPhone());
                em.merge(existingDoctor);
            }
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }

    // Delete a doctor by ID
    public void deleteDoctor(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Doctor doctor = em.find(Doctor.class, id);
            if (doctor != null) {
                em.remove(doctor);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Delete all doctors
    public void deleteAllDoctors() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Doctor").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Close the EntityManagerFactory when done with the repository
    public void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}

