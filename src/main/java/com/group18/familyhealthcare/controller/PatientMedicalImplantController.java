package com.group18.familyhealthcare.controller;

import com.group18.familyhealthcare.entity.PatientMedicalImplant;
import com.group18.familyhealthcare.service.PatientMedicalImplantService;
import com.group18.familyhealthcare.utility.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/patient/implant")
public class PatientMedicalImplantController {
    private final PatientMedicalImplantService medicalImplantService;

    @Autowired
    public PatientMedicalImplantController(PatientMedicalImplantService medicalImplantService) {
        this.medicalImplantService = medicalImplantService;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<Object> getMedicalImplant(@RequestParam("patientId") Long patientId){
        try {
            List<PatientMedicalImplant> patientMedicalImplantList = medicalImplantService.getMedicalImplant(patientId);
            return ResponseHandler.generateResponse("success", HttpStatus.OK, patientMedicalImplantList);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Object> addMedicalImplant(@RequestBody PatientMedicalImplant medicalImplant,
                                                   @RequestParam("patientId") Long patientId){
        try {
            PatientMedicalImplant patientMedicalImplant = medicalImplantService.addMedicalImplant(medicalImplant, patientId);
            return ResponseHandler.generateResponse("success", HttpStatus.OK, patientMedicalImplant);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Object> updateMedicalImplant(@RequestBody PatientMedicalImplant medicalImplant){
        try {
            medicalImplantService.updateMedicalImplant(medicalImplant);
            return ResponseHandler.generateResponse("Medical implant updated", HttpStatus.OK, null);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteMedicalImplant(@RequestParam("medicalImplantId") Long medicalImplantId){
        try {
            medicalImplantService.deleteMedicalImplant(medicalImplantId);
            return ResponseHandler.generateResponse("Medical implant deleted", HttpStatus.OK, null);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
}
