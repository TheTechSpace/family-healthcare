package com.group18.familyhealthcare.controller;

import com.group18.familyhealthcare.entity.PatientEmergencyCard;
import com.group18.familyhealthcare.service.PatientEmergencyCardService;
import com.group18.familyhealthcare.utility.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/patient/emergency")
public class PatientEmergencyCardController {
    private final PatientEmergencyCardService emergencyCardService;

    @Autowired
    public PatientEmergencyCardController(PatientEmergencyCardService emergencyCardService) {
        this.emergencyCardService = emergencyCardService;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<Object> getEmergencyCard(@RequestParam("patientId") Long patientId){
        try {
            List<PatientEmergencyCard> patientEmergencyCardList = emergencyCardService.getEmergencyCard(patientId);
            return ResponseHandler.generateResponse("success", HttpStatus.OK, patientEmergencyCardList);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Object> addEmergencyCard(@RequestBody PatientEmergencyCard emergencyCard,
                                                    @RequestParam("patientId") Long patientId){
        try {
            PatientEmergencyCard patientEmergencyCard = emergencyCardService.addEmergencyCard(emergencyCard, patientId);
            return ResponseHandler.generateResponse("success", HttpStatus.OK, patientEmergencyCard);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Object> updateEmergency(@RequestBody PatientEmergencyCard emergencyCard){
        try {
            emergencyCardService.updateEmergencyCard(emergencyCard);
            return ResponseHandler.generateResponse("Emergency details updated", HttpStatus.OK, null);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteEmergencyCard(@RequestParam("emergencyCardId") Long emergencyCardId){
        try {
            emergencyCardService.deleteEmergencyCard(emergencyCardId);
            return ResponseHandler.generateResponse("Emergency card deleted", HttpStatus.OK, null);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
}
