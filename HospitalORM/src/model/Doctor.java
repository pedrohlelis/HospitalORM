package model;

import java.util.List;

import jakarta.persistence.*;

public class Doctor extends Person {
	private String specialization;

	public Doctor(String name, String email, String cpf, String phone, String specialization) {
		super(name, email, cpf, phone);
		this.specialization = specialization;
	}
	
	@OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;
}
