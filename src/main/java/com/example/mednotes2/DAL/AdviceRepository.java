package com.example.mednotes2.DAL;

import com.example.mednotes2.Model.Advice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface AdviceRepository extends JpaRepository<Advice, Integer> {

    @Query("select a from Advice a where a.doctorEntity.doctorPersonalNumber=?1 and a.title=?2")
    Advice findAdviceByDoc(int doc , String title);

    @Query("select a from Advice a where a.title=?1")
    List<Advice> adviceExists(String title);

    @Query("select a from Advice a where a.doctorEntity.doctorName=?1 and a.doctorEntity.doctorSurname=?2")
    List<Advice> findAdviceByDocName(String emri , String mbiemri);

}
