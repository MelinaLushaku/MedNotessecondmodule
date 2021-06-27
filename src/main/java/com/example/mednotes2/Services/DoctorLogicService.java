package com.example.mednotes2.Services;

import com.example.mednotes2.DAL.AdviceRepository;
import com.example.mednotes2.Model.Advice;
import com.example.mednotes2.Model.DoctorEntity;
import com.example.mednotes2.Model.PatientEntity;
import com.example.mednotes2.OutPutAdapters.ISystemManagementServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.Date;
import java.util.List;

@Service
public class DoctorLogicService implements  IDoctorLogicService{

    @Autowired
    private AdviceRepository adviceRepository;
    @Autowired
    private ISystemManagementServices iSystemManagementServices;


    @Override
    public void addNewAdvice(String title , String content , Date data , int doctorPersonal){
        DoctorEntity doc = this.iSystemManagementServices.doctoriE(doctorPersonal);
        Advice a = new Advice(title, data, content , doc);
        this.adviceRepository.save(a);

    }


    @Override
    public void deleteAdvice(String title , int doctorPersonalN){
        DoctorEntity doc = this.iSystemManagementServices.doctoriE(doctorPersonalN);
        Advice a = this.adviceRepository.findAdviceByDoc(doctorPersonalN , title);
                this.adviceRepository.delete(a);
    }
    @Override
    public List<Advice> adviceExists(String title){
        List<Advice> lista = this.adviceRepository.adviceExists(title);
        return lista;
    }
    @Override
    public DoctorEntity docEnt(int nrPersonal){
        DoctorEntity docEn = this.iSystemManagementServices.doctoriE(nrPersonal);
        return docEn;
    }
    @Override
    public List<Advice> allAdvices(){
        return this.adviceRepository.findAll();
    }
    @Override
    public List<Advice> adviceByDocName(String name , String surname){
        List<Advice> lista = this.adviceRepository.findAdviceByDocName(name, surname);
        return lista;

    }
}
