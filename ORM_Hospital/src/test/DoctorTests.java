package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
		Doctor doctor = new Doctor("Andre Augusto", "dr_andre@gmail.com", "092808294399", "098390218309", "ginecologista");
		
		dm.saveDoctor(doctor);
		
		doctor.setEspecializacao("cardiologista");
		
		boolean success = dm.updateDoctor(doctor.getId(), doctor);
		assertEquals("cardiologista", doctor.getEspecializacao());
	}

}
