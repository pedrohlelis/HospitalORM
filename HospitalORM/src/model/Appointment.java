package model;

import jakarta.persistence.*;


public class Appointment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
    @ManyToOne
    @JoinColumn(name = "fk_patient_ID")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "fk_doctor_ID")
    private Doctor doctor;
    
	private String appointmentDate;
	private String status;
	private String reasonForVisit;
}
