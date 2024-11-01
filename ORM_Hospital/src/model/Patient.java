package model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Patient extends Person{
	
	private List<Appointment> appointments;
	
	public Patient(String nome, String email, String cpf, String phone) {
		super(nome, email, cpf, phone);

	}
	
	public Patient() {
		super();
	}
	
}
