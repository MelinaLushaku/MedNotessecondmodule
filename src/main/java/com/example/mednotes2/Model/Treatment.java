package com.example.mednotes2.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int treatmentId;

    @Column
    private String treatmentName;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="diagonisisId")
    private Diagnosis diagnosis;

    public Treatment(){}

    public Treatment(String treatmentName, Diagnosis diagnosis, Date startDate , Date endDate) {
        this.treatmentName = treatmentName;
        this.diagnosis = diagnosis;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(int treatmentId) {
        this.treatmentId = treatmentId;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public void setTreatmentName(String treatmentName) {
        this.treatmentName = treatmentName;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }
}
