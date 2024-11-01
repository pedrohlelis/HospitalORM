package model;

import java.util.List;

import jakarta.persistence.*;


@Entity
public class Patient extends Person {
	
	@OneToMany(mappedBy = "patient")
	private List<Appointment> appointments;
	
	@OneToOne(mappedBy = "patient")
	@JoinColumn(name = "medical_record_ID")
	private MedicalRecord medicalRecord;
	
	public Patient(String name, String email, String cpf, String phone) {
		super(name, email, cpf, phone);
		this.medicalRecord = new MedicalRecord();
	}
	
}
