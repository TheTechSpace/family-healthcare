package com.group18.familyhealthcare.controller;

import com.group18.familyhealthcare.entity.PatientMedication;
import com.group18.familyhealthcare.service.PatientMedicationService;
import com.group18.familyhealthcare.utility.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/patient/medication")
public class PatientMedicationController {
    private final PatientMedicationService patientMedicationService;

    @Autowired
    public PatientMedicationController(PatientMedicationService patientMedicationService) {
        this.patientMedicationService = patientMedicationService;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<Object> getMedication(@RequestParam("patientId") Long patientId){
        try {
            List<PatientMedication> patientMedicationList = patientMedicationService.getMedication(patientId);
            return ResponseHandler.generateResponse("success", HttpStatus.OK, patientMedicationList);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Object> addMedication(@RequestBody PatientMedication patientMedication,
                                                @RequestParam Long patientId){
        try {
            PatientMedication medication = patientMedicationService.addMedication(patientMedication, patientId);
            return ResponseHandler.generateResponse("success", HttpStatus.OK, medication);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Object> updateMedication(@RequestBody PatientMedication patientMedication){
        try {
            patientMedicationService.updateMedication(patientMedication);
            return ResponseHandler.generateResponse("Medication updated", HttpStatus.OK, null);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteMedication(@RequestParam("medicationId") Long medicationId){
        try {
            patientMedicationService.deleteMedication(medicationId);
            return ResponseHandler.generateResponse("Medication deleted", HttpStatus.OK, null);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
}
