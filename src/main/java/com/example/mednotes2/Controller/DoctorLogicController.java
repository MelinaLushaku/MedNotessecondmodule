package com.example.mednotes2.Controller;

import com.example.mednotes2.Helpers.DoctorResponse;
import com.example.mednotes2.Model.*;
import com.example.mednotes2.Services.IDoctorLogicService;
import com.sun.deploy.panel.AdvancedNetworkSettingsDialog;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import javax.ws.rs.Path;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/doctorLogicManagement")
public class DoctorLogicController {

    @Autowired
    private IDoctorLogicService iDoctorLogicService;



    @PostMapping("/addAdvice/{title}/{content}/{nrPersonal}")
    public DoctorResponse addNewAdvice(@PathVariable String title, @PathVariable String content , @PathVariable int nrPersonal){
        DoctorEntity docE = this.iDoctorLogicService.docEnt(nrPersonal);
        List<Advice> lista = this.iDoctorLogicService.adviceExists(title);
        if(lista.size() == 0 && docE != null){
            Date in = new Date();
            LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
            Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
            out.setHours(20);
            out.setMinutes(0);
            out.setSeconds(0);
            this.iDoctorLogicService.addNewAdvice(title, content, out, nrPersonal);
            return new DoctorResponse.DoctorResponseBuilder<>(201).setMesazhin("Advice added successfully").build();
        }
        return new DoctorResponse.DoctorResponseBuilder<>(401).setErrorin("This advice already exists!").build();
    }

    @PostMapping("/deleteAdvice/{title}/{docId}")
    public DoctorResponse deleteAdvice(@PathVariable String title , @PathVariable int docId){
        List<Advice> lista = this.iDoctorLogicService.adviceExists(title);
        if(lista.size() !=0){
            DoctorEntity docE = lista.get(0).getDoctorEntity();
            int docEnt = docE.getDoctorPersonalNumber();
            if(docEnt == docId){
                this.iDoctorLogicService.deleteAdvice(title, docId);
                return new DoctorResponse.DoctorResponseBuilder<>(201).setMesazhin("Advice is deleted successfully!").build();
            }
            else{
                return new DoctorResponse.DoctorResponseBuilder<>(401).setErrorin("You can't delete other peoples advices!").build();
            }
        }
        return new DoctorResponse.DoctorResponseBuilder<>(401).setErrorin("This advice doesn't exists!").build();
    }
    //lidhe front
    @GetMapping("/allAdivce")
    public DoctorResponse getAllAdvice() {
        List<Advice> lista = this.iDoctorLogicService.allAdvices();
        if (lista.size() != 0) {
            return new DoctorResponse.DoctorResponseBuilder<>(201).setMesazhin("List e suksesshme").setData(lista).build();
        }
        return new DoctorResponse.DoctorResponseBuilder<>(401).setErrorin("There are no Advice's").build();
    }
    //lidhe front
    @GetMapping("/adviceBy/{emri}/{mbiemri}")
    public DoctorResponse getAdviceByDoc(@PathVariable String emri , @PathVariable String mbiemri){
        List<Advice> lista = this.iDoctorLogicService.adviceByDocName(emri, mbiemri);
        if(lista.size() != 0){
            return new DoctorResponse.DoctorResponseBuilder<>(201).setMesazhin("List e suksseshme").setData(lista).build();
        }
        return  new DoctorResponse.DoctorResponseBuilder<>(401).setErrorin("There are no advices from this doctor").build();
    }

    //LIDHE ME FRONT
    @GetMapping("/totalAdvice")
    public DoctorResponse getTotalAdivce(){
        int totalAdvice = this.iDoctorLogicService.totalAdvice();
        return new DoctorResponse.DoctorResponseBuilder<>(201).setMesazhin("Vlere e sukseshme").setData(totalAdvice).build();
    }

    @PostMapping("/addDiagnosis/{docId}/{patId}/{tN}/{dN}/{sD}/{eD}")
    public DoctorResponse addDiagnosis(@PathVariable int docId , @PathVariable int patId , @PathVariable String tN , @PathVariable String dN , @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date sD , @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date eD ){
          sD.setHours(20);
          sD.setMinutes(0);
          sD.setMinutes(0);
          eD.setHours(20);
          eD.setMinutes(0);
          eD.setMinutes(0);
        Date in = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        out.setHours(20);
        out.setMinutes(0);
        out.setSeconds(0);
          if(eD.compareTo(sD) > 0 && sD.compareTo(out) > 0) {
              this.iDoctorLogicService.addDiagnosis(patId, docId, tN, dN, sD, eD);
              return new DoctorResponse.DoctorResponseBuilder<>(201).setMesazhin("Diagnosis added!").build();
          }
          return new DoctorResponse.DoctorResponseBuilder<>(401).setErrorin("Dates for treatment should be after one other!").build();
    }
// PO BOHET METODA REKURSIVE
    @GetMapping("/getDiagnosisByPat/{patId}")
    public DoctorResponse getDiagnosisByPat(@PathVariable int patId){

        List<Diagnosis> lista = this.iDoctorLogicService.getDiagnosisByPatient(patId);

        if(lista.size() != 0){
            return new DoctorResponse.DoctorResponseBuilder<>(201).setMesazhin("List e suksesshe").setData(lista).build();
        }
            return new DoctorResponse.DoctorResponseBuilder<>(401).setErrorin("You don't have any diagnosis!").build();

    }
    //lidhe front
    @PostMapping("/editTreatment/{dID}/{eD}/{tN}")
    public DoctorResponse editTreatmemt(@PathVariable int dID ,  @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date eD , @PathVariable String tN){
        if(eD != null  || tN != null){
            this.iDoctorLogicService.editTreatment(dID, eD, tN);
            return new DoctorResponse.DoctorResponseBuilder<>(201).setMesazhin("Treatment edited successfully").build();
        }
        return new DoctorResponse.DoctorResponseBuilder<>(401).setErrorin("You didn't choose what to edit!").build();
    }
    //lidhe front
    @PostMapping("/deleteTreatment/{treatId}")
    public DoctorResponse deleteTreatment(@PathVariable int treatId){
        Treatment t = this.iDoctorLogicService.getTreatment(treatId);

        if(t != null){
            this.iDoctorLogicService.deleteTreatment(t);
            return  new DoctorResponse.DoctorResponseBuilder<>(201).setMesazhin("Treatment Deleted!").build();
        }
        return  new DoctorResponse.DoctorResponseBuilder<>(401).setErrorin("You didn't choose a treatment").build();
    }
    //lidhe front
    @PostMapping("/deleteDiseases/{dId}")
    public DoctorResponse deleteDisease(@PathVariable int dId){
        Diseases t = this.iDoctorLogicService.getDiseases(dId);

        if(t != null){
            this.iDoctorLogicService.deleteDiseases(t);
            return  new DoctorResponse.DoctorResponseBuilder<>(201).setMesazhin("Disease is  Deleted!").build();
        }
        return  new DoctorResponse.DoctorResponseBuilder<>(401).setErrorin("You didn't choose a treatment").build();
    }
}