package com.example.mednotes2.Services;

import com.example.mednotes2.Model.Advice;
import com.example.mednotes2.Model.Diagnosis;
import com.example.mednotes2.Model.DoctorEntity;

import java.util.Date;
import java.util.List;

public interface IDoctorLogicService {
    void addNewAdvice(String title , String content , Date data , int doctorPersonal);
    void deleteAdvice(String title , int doctorPersonalN);
    List<Advice> adviceExists(String title);
    DoctorEntity docEnt(int nrPersonal);
    List<Advice> allAdvices();
    List<Advice>adviceByDocName(String name , String surname);
    void addDiagnosis(int patientEntity, int doctorEntity, String treatmentName, String diseasesName, Date startDate, Date endDate);
    List<Diagnosis> getDiagnosisByPatient(int patId);
    void editTreatment(int diagnosisId , Date eD , String treatmenN);


}
