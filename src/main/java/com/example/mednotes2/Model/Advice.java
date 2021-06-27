package com.example.mednotes2.Model;

import javax.persistence.*;
import javax.print.Doc;
import java.util.Date;

@Entity
public class Advice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int adviceId;

    @Column
    private String title;

    @Column
    private Date addedDate;

    @Column
    private String content;

    @Embedded
    private DoctorEntity doctorEntity;


    public Advice(){}

    public Advice(String title, Date addedDate, String content, DoctorEntity doctorEntity) {
        this.title = title;
        this.addedDate = addedDate;
        this.content = content;
        this.doctorEntity = doctorEntity;
    }

    public DoctorEntity getDoctorEntity() {
        return doctorEntity;
    }

    public void setDoctorEntity(DoctorEntity doctorEntity) {
        this.doctorEntity = doctorEntity;
    }

    public int getAdviceId() {
        return adviceId;
    }

    public void setAdviceId(int adviceId) {
        this.adviceId = adviceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
