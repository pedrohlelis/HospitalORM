package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Patient;
import services.PatientsManager;

import java.util.List;

class ListarPacientesTest {

    private PatientsManager pm = PatientsManager.getInstance();

    @Test
    void testListarPacientes() {
        pm.deleteAllPatients();
        
        Patient patient1 = new Patient("Paciente A", "pacienteA@gmail.com", "11111111111", "9999-9999");
        Patient patient2 = new Patient("Paciente B", "pacienteB@gmail.com", "22222222222", "8888-8888");
        pm.savePatient(patient2);
        pm.savePatient(patient1);

        List<Patient> patients = pm.getAllPatients();

        assertEquals(2, patients.size(), "Devem existir 2 pacientes persistidos no banco.");
    }

}