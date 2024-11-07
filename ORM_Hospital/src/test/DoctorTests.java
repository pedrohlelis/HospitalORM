package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import model.Appointment;
import model.Doctor;
import services.DoctorsManager;

class DoctorTests {
	private DoctorsManager dm = DoctorsManager.getInstance();
	
	@Test
	void createDoctorTest() {
		Doctor doctor = new Doctor("Andre Augusto", "dr_andre@gmail.com", "092808294399", "098390218309", "ginecologista");
		boolean success = dm.saveDoctor(doctor);
		assertTrue(success);
	}
	
	@Test
	void editDoctorTest() {
		Doctor doctor = new Doctor("Andre Augustos", "dr_andre@gmail.com", "092808294399", "098390218309", "ginecologista");
		
		dm.saveDoctor(doctor);
		
		doctor.setEspecializacao("cardiologista");
		
		boolean success = dm.updateDoctor(doctor.getId(), doctor);
		
		Doctor retrievedDoctor = dm.getDoctorById(doctor.getId());
		assertEquals("cardiologista", retrievedDoctor.getEspecializacao());
	}
	
	@Test
	void testRemove() {
		
	    Doctor doctor = new Doctor("Joao Doutor2", "joaoD@gmail.com", "2323232", "3443-4434", "pediatra");	    
	    
	    dm.saveDoctor(doctor);

	    
	    Doctor retrievedDoctor = dm.getDoctorById(doctor.getId());


	    if (retrievedDoctor != null) {
	        dm.deleteDoctorById(retrievedDoctor.getId());
	    }


	    Doctor deletedDoctor = dm.getDoctorById(doctor.getId());
	    
	    assertNull(deletedDoctor, "The doctor should have been deleted.");
	}
	
	@Test
	void testGetSystemDoctors() {
	    dm.deleteAllDoctors();
	    
	    Doctor doctor1 = new Doctor("Pedro Doutor1", "pedroD@gmail.com", "987654321", "8765-4321", "cardiologista");
	    Doctor doctor2 = new Doctor("Ana Doutora2", "anaD@gmail.com", "123456789", "1234-5678", "neurologista");
	    
	    dm.saveDoctor(doctor1);
	    dm.saveDoctor(doctor2);
	    
	    List<Doctor> doctors = dm.getAllDoctors();
	    
	    assertEquals(2, doctors.size(), "A listagem deve retornar exatamente 2 médicos.");
	}

}
