package model;

import jakarta.persistence.*;


public class MedicalRecord {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@OneToOne(mappedBy = "patient")
	@JoinColumn(name = "id")
	private Patient patient;
	
	public MedicalRecord() {
		
	}
}
