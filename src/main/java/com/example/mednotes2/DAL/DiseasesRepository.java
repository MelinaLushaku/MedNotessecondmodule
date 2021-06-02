package com.example.mednotes2.DAL;

import com.example.mednotes2.Model.Diseases;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseasesRepository extends JpaRepository<Diseases, Integer> {
}
