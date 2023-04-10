package com.group18.familyhealthcare.controller;

import com.group18.familyhealthcare.entity.PatientVaccination;
import com.group18.familyhealthcare.service.PatientVaccinationService;
import com.group18.familyhealthcare.utility.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/patient/vaccination")
public class PatientVaccinationController {
    private final PatientVaccinationService vaccinationService;

    @Autowired
    public PatientVaccinationController(PatientVaccinationService vaccinationService) {
        this.vaccinationService = vaccinationService;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<Object> getVaccination(@RequestParam("patientId") Long patientId){
        try {
            List<PatientVaccination> patientVaccinationListList = vaccinationService.getVaccination(patientId);
            return ResponseHandler.generateResponse("success", HttpStatus.OK, patientVaccinationListList);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Object> addVaccination(@RequestBody PatientVaccination patientVaccination,
                                                 @RequestParam("patientId") Long patientId){
        try {
            PatientVaccination vaccination = vaccinationService.addVaccination(patientVaccination, patientId);
            return ResponseHandler.generateResponse("success", HttpStatus.OK, vaccination);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Object> updateVaccination(@RequestBody PatientVaccination patientVaccination){
        try {
            vaccinationService.updateVaccination(patientVaccination);
            return ResponseHandler.generateResponse("Vaccination details updated", HttpStatus.OK, null);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteVaccination(@RequestParam("vaccinationId") Long vaccinationId){
        try {
            vaccinationService.deleteVaccination(vaccinationId);
            return ResponseHandler.generateResponse("Vaccination deleted", HttpStatus.OK, null);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
}
