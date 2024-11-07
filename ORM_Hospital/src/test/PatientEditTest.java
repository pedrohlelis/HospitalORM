package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Patient;
import services.PatientsManager;

class PatientEditTest {
	
	private PatientsManager pm = PatientsManager.getInstance();

	@Test
	void editPatientTest() {
		Patient patient = new Patient("Joao Paciente123", "joaoP@gmail.com", "2323232", "3443-4434");
		
		pm.savePatient(patient);
		
		patient.setEmail("Pjoao@hotmail.com");
		
		pm.updatePatient(patient.getId(), patient);
		
		Patient retrievedPatient = pm.getPatientById(patient.getId());
		assertEquals("Pjoao@hotmail.com", retrievedPatient.getEmail());
	}
}
