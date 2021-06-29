package com.example.mednotes2.DAL;

import com.example.mednotes2.Model.Advice;
import com.example.mednotes2.Model.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Integer> {
    @Query("select a from Diagnosis a where a.patientEntity.personalNumber=?1")
    List<Diagnosis> getDiagnosisByPat(int patId);

    @Query("select a from Diagnosis a where a.patientEntity.personalNumber=?1 and a.doctorEntity.doctorPersonalNumber=?1")
    List<Diagnosis> getDiagnosisByPatDoc(int patId , int docId);

}
