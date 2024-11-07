package services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Patient;

public class PatientService {
    
    private EntityManager entityManager;

    public PatientService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    
    public void registerPatient(Patient patient) {
        EntityTransaction transaction = entityManager.getTransaction();
        
        try {
            transaction.begin();
            entityManager.persist(patient);
            transaction.commit();
            System.out.println("Paciente cadastrado com sucesso.");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
   
    
    public void deletePatient(Patient patient){
        EntityTransaction transaction = entityManager.getTransaction();
        
        try {
            transaction.begin();
            
            
            Patient managedPatient = entityManager.find(Patient.class, patient.getId());
            if (managedPatient != null) {
                entityManager.remove(managedPatient);
                System.out.println("Paciente deletado com sucesso.");
            } else {
                System.out.println("Paciente não encontrado.");
            }
            
            transaction.commit();
            
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}
