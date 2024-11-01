package model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Diagnosis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diagnosis_ID")
    private long id;

    @ManyToOne
    @JoinColumn(name = "fk_patient_ID")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "fk_appointment_ID", nullable = true)
    private Appointment appointment;

    private String condition;
    private String treatment;
    private Date diagnosisDate;
    private String notes;


    public Diagnosis() {}

    public Diagnosis(Patient patient, Appointment appointment, String condition, String treatment, Date diagnosisDate, String notes) {
        this.patient = patient;
        this.appointment = appointment;
        this.condition = condition;
        this.treatment = treatment;
        this.diagnosisDate = diagnosisDate;
        this.notes = notes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public Date getDiagnosisDate() {
        return diagnosisDate;
    }

    public void setDiagnosisDate(Date diagnosisDate) {
        this.diagnosisDate = diagnosisDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}