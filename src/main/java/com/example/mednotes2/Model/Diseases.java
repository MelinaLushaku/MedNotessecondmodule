package com.example.mednotes2.Model;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
public class Diseases {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int diseasesId;

    @Column
    private String diseaseName;


    @ManyToOne
    @JoinColumn(name="diagonisisId")
    private Diagnosis diagnosis;

    public Diseases(){}

    public Diseases(String diseaseName , Diagnosis diagnosis) {
        this.diseaseName = diseaseName;
        this.diagnosis = diagnosis;
    }

    public int getDiseasesId() {
        return diseasesId;
    }

    public void setDiseasesId(int diseasesId) {
        this.diseasesId = diseasesId;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }
}
