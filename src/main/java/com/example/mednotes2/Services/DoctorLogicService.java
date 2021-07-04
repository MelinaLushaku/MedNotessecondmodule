package com.example.mednotes2.Services;

import com.example.mednotes2.DAL.AdviceRepository;
import com.example.mednotes2.DAL.DiagnosisRepository;
import com.example.mednotes2.DAL.DiseasesRepository;
import com.example.mednotes2.DAL.TreatmentRepository;
import com.example.mednotes2.Model.*;
import com.example.mednotes2.OutPutAdapters.ISystemManagementServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DoctorLogicService implements  IDoctorLogicService{

    @Autowired
    private AdviceRepository adviceRepository;
    @Autowired
    private DiagnosisRepository diagnosisRepository;
    @Autowired
    private TreatmentRepository treatmentRepository;
    @Autowired
    private DiseasesRepository diseasesRepository;
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

    @Override
    public void addDiagnosis(int patientEntity , int doctorEntity , String treatmentName, String diseasesName, Date startDate , Date endDate ){
        PatientEntity pa = this.iSystemManagementServices.pacientiE(patientEntity);
        DoctorEntity doc = this.iSystemManagementServices.doctoriE(doctorEntity);
        Date in = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        //DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        //String outWithZeroTime = formatter.format(out);
        out.setHours(20);
        out.setMinutes(0);
        out.setSeconds(0);
        Diagnosis dg = new Diagnosis(out , pa , doc);
        this.diagnosisRepository.save(dg);
        Diseases ds = new Diseases(diseasesName , dg);
        this.diseasesRepository.save(ds);
        Treatment t = new Treatment(treatmentName, dg , startDate , endDate);
        this.treatmentRepository.save(t);
    }
    @Override
    public List<Diagnosis> getDiagnosisByPatient(int patId){
        List<Diagnosis> lista = this.diagnosisRepository.getDiagnosisByPat(patId);
        return lista;
    }

    @Override
    public void editTreatment(int diagnosisId , Date eD , String treatmenN , int patID){
        Optional<Diagnosis> lista = this.diagnosisRepository.findById(diagnosisId);
        List<Treatment> treatments = this.treatmentRepository.editTreatment(lista.get() , patID);
       // List<Diagnosis> diagnoses = this.diagnosisRepository.getDiagnosisByPatDoc(patientId , docId);
        if(eD == null && treatmenN != null){
            treatments.get(0).setTreatmentName(treatmenN);
        }else if (eD != null && treatmenN == null){
            treatments.get(0).setEndDate(eD);
        }else{
            treatments.get(0).setTreatmentName(treatmenN);
            treatments.get(0).setEndDate(eD);
        }

        Date in = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        out.setHours(20);
        out.setMinutes(0);
        out.setSeconds(0);
        lista.get().setDateOfChange(out);
        this.treatmentRepository.save(treatments.get(0));

    }

    public Treatment getTreatment(int treatmentId , int patId){
        Treatment treatime = this.treatmentRepository.deleteTreatment(treatmentId, patId);
        return treatime;
    }

    public void deleteTreatment(Treatment t){
        Diagnosis dis2 = t.getDiagnosis();
        Date in = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        out.setHours(20);
        out.setMinutes(0);
        out.setSeconds(0);
        dis2.setDateOfChange(out);
        Set<Treatment> seti1 = dis2.getTreatment();
        Set<Diseases> seti2 = dis2.getDiseases();

            this.treatmentRepository.delete(t);

    }
    @Override
    public Diseases getDiseases(int dID , int patId){

        Diseases dis= this.diseasesRepository.deleteDiseases(dID , patId);

        return dis;
    }
    public void  deleteDiseases(Diseases d){
        Diagnosis dis2 = d.getDiagnosis();
        Date in = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        out.setHours(20);
        out.setMinutes(0);
        out.setSeconds(0);
        dis2.setDateOfChange(out);

            this.diseasesRepository.delete(d);


    }

   @Override
   public List<Treatment> byDiagnosis(int id){
        Optional<Diagnosis> lista = this.diagnosisRepository.findById(id);
        Diagnosis a = lista.get();
        PatientEntity p = a.getPatientEntity();
        List<Treatment> lista2 = this.treatmentRepository.editTreatment(a, p.getPersonalNumber());
        return lista2;
}
   @Override
    public List<Diseases> byDiagnosisd(int id){
       Optional<Diagnosis> lista = this.diagnosisRepository.findById(id);
       Diagnosis a = lista.get();
       List<Diseases> lista2 = this.diseasesRepository.editDiseases(a);
       return lista2;
   }

   @Override
    public  int totalAdvice(){
        List<Advice> lista = this.adviceRepository.findAll();
        return lista.size();
   }

   @Override
    public PatientEntity pat(int id){
       PatientEntity patE = this.iSystemManagementServices.pacientiE(id);
       return patE;
   }
   @Override
    public Optional<Diagnosis> exists(int dId){
        Optional<Diagnosis> lista = this.diagnosisRepository.findById(dId);
        return lista;
   }
}
