package com.example.mednotes2.DAL;

import com.example.mednotes2.Model.Advice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdviceRepository extends JpaRepository<Advice, Integer> {
}
