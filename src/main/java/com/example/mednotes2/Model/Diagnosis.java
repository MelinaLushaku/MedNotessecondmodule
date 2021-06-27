package com.example.mednotes2.Model;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int diagnosisId;

    @Column
    private Date dateOfChange;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "diagnosis")
    private Set<Diseases> diseases;
    public Set<Diseases> getDiseases() {
        return diseases;
    }
    public void setDiseases(Set<Diseases> v) {
        this.diseases = diseases;
    }

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "diagnosis")
    private Set<Treatment> treatment;
    public Set<Treatment> getTreatment() {
        return treatment;
    }
    public void setTreatment(Set<Treatment> v) {
        this.treatment = treatment;
    }

    @Embedded
    private PatientEntity patientEntity;

    @Embedded
    private DoctorEntity doctorEntity;



    public Diagnosis(){}

    public Diagnosis(Date dateOfChange , PatientEntity patientEntity , DoctorEntity doctorEntity) {
        this.dateOfChange = dateOfChange;
        this.patientEntity = patientEntity;
        this.doctorEntity =doctorEntity;
    }

    public PatientEntity getPatientEntity() {
        return patientEntity;
    }

    public void setPatientEntity(PatientEntity patientEntity) {
        this.patientEntity = patientEntity;
    }

    public DoctorEntity getDoctorEntity() {
        return doctorEntity;
    }

    public void setDoctorEntity(DoctorEntity doctorEntity) {
        this.doctorEntity = doctorEntity;
    }

    public int getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(int diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    public Date getDateOfChange() {
        return dateOfChange;
    }

    public void setDateOfChange(Date dateOfChange) {
        this.dateOfChange = dateOfChange;
    }
}
