package com.group18.familyhealthcare.controller;

import com.group18.familyhealthcare.entity.PatientVitals;
import com.group18.familyhealthcare.service.PatientVitalsService;
import com.group18.familyhealthcare.utility.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/patient/vitals")
public class PatientVitalsController {
    private final PatientVitalsService patientVitalsService;

    @Autowired
    public PatientVitalsController(PatientVitalsService patientVitalsService) {
        this.patientVitalsService = patientVitalsService;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<Object> getVitals(@RequestParam("patientId") Long patientId){
        try {
            List<PatientVitals> patientVitalsList = patientVitalsService.getVitals(patientId);
            return ResponseHandler.generateResponse("success", HttpStatus.OK, patientVitalsList);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Object> addVitals(@RequestBody PatientVitals vitals,
                                                @RequestParam("patientId") Long patientId){
        try {
            PatientVitals savedVitals = patientVitalsService.addVitals(vitals, patientId);
            return ResponseHandler.generateResponse("success", HttpStatus.OK, savedVitals);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Object> updateVitals(@RequestBody PatientVitals vitals){
        try {
            patientVitalsService.updateVitals(vitals);
            return ResponseHandler.generateResponse("Vitals updated", HttpStatus.OK, null);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteMedication(@RequestParam("vitalsId") Long vitalsId){
        try {
            patientVitalsService.deleteVitals(vitalsId);
            return ResponseHandler.generateResponse("Vitals deleted", HttpStatus.OK, null);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
}
