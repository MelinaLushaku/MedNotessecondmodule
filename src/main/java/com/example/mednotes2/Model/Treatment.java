package com.example.mednotes2.Model;

import javax.persistence.*;

@Entity
public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int treatmentId;

    @Column
    private String treatmentName;

    @ManyToOne
    @JoinColumn(name="diagonisisId")
    private Diagnosis diagnosis;

    public Treatment(){}

    public Treatment(String treatmentName, Diagnosis diagnosis) {
        this.treatmentName = treatmentName;
        this.diagnosis = diagnosis;
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
