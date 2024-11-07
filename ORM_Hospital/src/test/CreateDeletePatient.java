package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Patient;
import services.PatientService;

class CreateDeletePatient {
	 private static EntityManagerFactory emf;
	    private EntityManager em;
	    private PatientService patientService;

	    @BeforeAll
	    public static void setUpClass() {
	        
	        emf = Persistence.createEntityManagerFactory("hospitalPU");
	    }

	    @AfterAll
	    public static void tearDownClass() {
	        if (emf != null) {
	            emf.close();
	        }
	    }

	    @BeforeEach
	    public void setUp() {
	        em = emf.createEntityManager();
	        patientService = new PatientService(em);
	    }

	    @AfterEach
	    public void tearDown() {
	        if (em != null) {
	            em.close();
	        }
	    }

	    @Test
	    public void testRegisterPatient() {
	        
	        Patient patient = new Patient("John Doe", "john.doe@example.com", "12345678900", "123456789");
	        
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hospitalPU");
	        EntityManager em = emf.createEntityManager();
	        
	        PatientService patientService = new PatientService(em);
	        patientService.registerPatient(patient);
	        
	        
	        
	        Patient savedPatient = em.find(Patient.class, patient.getId());
	        assertNotNull(savedPatient);
	        assertEquals("John Doe", savedPatient.getName());
	        assertEquals("john.doe@example.com", savedPatient.getEmail());
	        assertEquals("12345678900", savedPatient.getCpf());
	        assertEquals("123456789", savedPatient.getPhone());
	    }
	    
	   
	        
	    	@Test
	    public void testDeletePatient() {
	    	    
	    	  Patient patient = new Patient("Jane Doe", "jane.doe@example.com", "98765432100", "987654321");
	    	  EntityManagerFactory emf = Persistence.createEntityManagerFactory("hospitalPU");
		      EntityManager em = emf.createEntityManager();
		        
		      PatientService patientService = new PatientService(em);
		      patientService.registerPatient(patient);
		        
	    	  
	    	  
	    	    
	    	  em.getTransaction().begin();
	    	  em.persist(patient);
	    	  em.getTransaction().commit();

	    	    
	    	   patientService.deletePatient(patient);

	    	    
	    	   Patient deletedPatient = em.find(Patient.class, patient.getId());
	    	   assertNull(deletedPatient, "O paciente deveria ter sido deletado.");
	    	}

	}


