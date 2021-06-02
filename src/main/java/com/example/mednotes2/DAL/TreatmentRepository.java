package com.example.mednotes2.DAL;

import com.example.mednotes2.Model.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentRepository extends JpaRepository<Treatment, Integer> {
}
