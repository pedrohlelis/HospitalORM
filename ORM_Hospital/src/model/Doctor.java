package model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Doctor extends Person{
	
	private String especializacao;
	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
	private List<Appointment> appointments;
	
	public Doctor(String nome, String email, String cpf, String fone, String especializacao) {
		super(nome, email, cpf, fone);
		this.especializacao = especializacao;
		this.appointments = new ArrayList<>();
	}
	
	public Doctor() {
		
	}
	
	public List<Appointment> getAppointments(){
		return appointments;
	}
	
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
        appointment.setDoctor(this);
    }
	
    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
    }
	
	public String getEspecializacao() {
		return especializacao;
	}
	
	public void setEspecializacao(String especializacao) {
		this.especializacao = especializacao;
	}
	
}