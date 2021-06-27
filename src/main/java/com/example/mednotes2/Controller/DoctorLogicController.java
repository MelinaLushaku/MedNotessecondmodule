package com.example.mednotes2.Controller;

import com.example.mednotes2.Helpers.DoctorResponse;
import com.example.mednotes2.Model.Advice;
import com.example.mednotes2.Model.DoctorEntity;
import com.example.mednotes2.Services.IDoctorLogicService;
import com.sun.deploy.panel.AdvancedNetworkSettingsDialog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import javax.ws.rs.Path;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/doctorLogicManagement")
public class DoctorLogicController {

    @Autowired
    private IDoctorLogicService iDoctorLogicService;



    @PostMapping("/addAdvice/{title}/{content}/{date}/{nrPersonal}")
    public DoctorResponse addNewAdvice(@PathVariable String title, @PathVariable String content , @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date , @PathVariable int nrPersonal){
        DoctorEntity docE = this.iDoctorLogicService.docEnt(nrPersonal);
        List<Advice> lista = this.iDoctorLogicService.adviceExists(title);
        if(lista.size() == 0 && docE != null){
            this.iDoctorLogicService.addNewAdvice(title, content, date, nrPersonal);
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

    @GetMapping("/allAdivce")
    public DoctorResponse getAllAdvice(){
        List<Advice> lista = this.iDoctorLogicService.allAdvices();
        if(lista.size() != 0) {
            return new DoctorResponse.DoctorResponseBuilder<>(201).setMesazhin("List e suksesshme").setData(lista).build();
        }
        return new DoctorResponse.DoctorResponseBuilder<>(401).setErrorin("There are no Advice's").build();
        }

    @GetMapping("/adviceBy/{emri}/{mbiemri}")
    public DoctorResponse getAdviceByDoc(@PathVariable String emri , @PathVariable String mbiemri){
        List<Advice> lista = this.iDoctorLogicService.adviceByDocName(emri, mbiemri);
        if(lista.size() != 0){
            return new DoctorResponse.DoctorResponseBuilder<>(201).setMesazhin("List e suksseshme").setData(lista).build();
        }
        return  new DoctorResponse.DoctorResponseBuilder<>(401).setErrorin("There are no advices from this doctor").build();
    }
}