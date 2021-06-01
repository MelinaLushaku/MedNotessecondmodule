package com.example.mednotes2.Model;

import javax.persistence.*;
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

    public Advice(){}

    public Advice(String title, Date addedDate, String content) {
        this.title = title;
        this.addedDate = addedDate;
        this.content = content;
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
