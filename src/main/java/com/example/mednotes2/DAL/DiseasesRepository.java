package com.example.mednotes2.DAL;

import com.example.mednotes2.Model.Diagnosis;
import com.example.mednotes2.Model.Diseases;
import com.example.mednotes2.Model.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiseasesRepository extends JpaRepository<Diseases, Integer> {
    @Query("select a from Diseases a where a.diagnosis=?1")
    List<Diseases> editDiseases(Diagnosis treatmentId);

    @Query("select a from Diseases a where a.diseasesId=?1 and a.diagnosis.patientEntity.personalNumber=?2")
    Diseases deleteDiseases(int dId, int patId);
}
