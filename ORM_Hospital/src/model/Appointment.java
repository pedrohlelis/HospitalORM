package model;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@Entity
public class Appointment {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Date appointmentDate;
	
	@ManyToOne
	@JoinColumn(name="doctor_id")
	private Doctor doctor;
	
	@ManyToOne
	@JoinColumn(name="patient_id")
	private Patient patient;
	
	@Transient
	private DateFormat dateFormatter =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public Appointment() {
		
	}
	
	public Appointment(String appointmentDate, Doctor doctor, Patient cliente) throws ParseException {
		this.appointmentDate = dateFormatter.parse(appointmentDate);
		this.doctor = doctor;
		this.patient = cliente;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getAppointmentDate() {
		return dateFormatter.format(appointmentDate);
	}
	
	public void setAppointmentDate(String appointmentDate) throws ParseException {
		this.appointmentDate = dateFormatter.parse(appointmentDate);
	}
	
	public Doctor getDoctor() {
		return doctor;
	}
	
	public void setDoctor(Doctor livro) {
		this.doctor = livro;
	}
	
	public Patient getPatient() {
		return patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public DateFormat getDateFormatter() {
		return dateFormatter;
	}
	
}