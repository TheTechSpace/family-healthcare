package com.group18.familyhealthcare.controller;

import com.group18.familyhealthcare.entity.Patient;
import com.group18.familyhealthcare.service.PatientService;
import com.group18.familyhealthcare.utility.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/patient")
public class PatientController {
    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<Object> getAllPatient(@RequestParam("accountId") Long accountId){
        try {
            List<Patient> patientList = patientService.getAllPatient(accountId);
            return ResponseHandler.generateResponse("success", HttpStatus.OK, patientList);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @GetMapping(value = "/details")
    public ResponseEntity<Object> patientDetails(@RequestParam("patientId") Long patientId){
        try {
            Object patientDetails = patientService.patientDetails(patientId);
            System.out.println("patient = " + patientDetails);
            return ResponseHandler.generateResponse("success", HttpStatus.OK, patientDetails);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Object> addPatient(@RequestBody Patient patient, @RequestParam("accountId") Long accountId){
        try {
            System.out.println("patient = " + patient);
            System.out.println("i = " +accountId);
            Object data = patientService.addPatient(patient, accountId);
            return ResponseHandler.generateResponse("Patient added successfully", HttpStatus.OK, data);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @DeleteMapping(value = "/delete")
    public Object deletePatient(@RequestParam("patientId") Long patientId){
        try {
            patientService.deletePatient(Long.valueOf(patientId));
            return ResponseHandler.generateResponse("Patient deleted", HttpStatus.OK, null);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PutMapping(value = "/update")
    public Object updatePatient(@RequestBody Patient patient){
        try {
            patientService.updatePatient(patient);
            return ResponseHandler.generateResponse("Patient updated", HttpStatus.OK, null);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
}
