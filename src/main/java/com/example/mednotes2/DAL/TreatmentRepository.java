package com.example.mednotes2.DAL;

import com.example.mednotes2.Model.Diagnosis;
import com.example.mednotes2.Model.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TreatmentRepository extends JpaRepository<Treatment, Integer> {
    @Query("select a from Treatment a where a.diagnosis=?1")
    List<Treatment> editTreatment(Diagnosis treatmentId);
}
