package com.group18.familyhealthcare.controller;

import com.group18.familyhealthcare.entity.PatientMedicalReport;
import com.group18.familyhealthcare.service.PatientMedicalReportService;
import com.group18.familyhealthcare.utility.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/patient/report")
public class PatientMedicalReportController {
    private final PatientMedicalReportService medicalReportService;

    @Autowired
    public PatientMedicalReportController(PatientMedicalReportService medicalReportService) {
        this.medicalReportService = medicalReportService;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<Object> getMedicalReport(@RequestParam("patientId") Long patientId){
        try {
            List<PatientMedicalReport> patientMedicalReportList = medicalReportService.getMedicalReport(patientId);
            return ResponseHandler.generateResponse("success", HttpStatus.OK, patientMedicalReportList);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Object> addMedicalReport(@RequestBody PatientMedicalReport medicalReport,
                                                @RequestParam("patientId") Long patientId){
        try {
            PatientMedicalReport patientMedicalReport = medicalReportService.addMedicalReport(medicalReport, patientId);
            return ResponseHandler.generateResponse("success", HttpStatus.OK, patientMedicalReport);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Object> updateMedicalReport(@RequestBody PatientMedicalReport medicalReport){
        try {
            medicalReportService.updateMedicalReport(medicalReport);
            return ResponseHandler.generateResponse("Medical report updated", HttpStatus.OK, null);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteMedicalReport(@RequestParam("medicalReportId") Long medicalReportId){
        try {
            medicalReportService.deleteMedicalReport(medicalReportId);
            return ResponseHandler.generateResponse("Medical report deleted", HttpStatus.OK, null);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
}
