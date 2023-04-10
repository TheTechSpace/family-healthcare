package com.group18.familyhealthcare.controller;

import com.group18.familyhealthcare.entity.PatientAppointment;
import com.group18.familyhealthcare.service.PatientAppointmentService;
import com.group18.familyhealthcare.utility.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/patient/appointment")
public class PatientAppointmentController {
    private final PatientAppointmentService appointmentService;

    @Autowired
    public PatientAppointmentController(PatientAppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<Object> getAppointment(@RequestParam("patientId") Long patientId){
        try {
            List<PatientAppointment> patientAppointmentList = appointmentService.getAppointment(patientId);
            return ResponseHandler.generateResponse("success", HttpStatus.OK, patientAppointmentList);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Object> addAppointment(@RequestBody PatientAppointment patientAppointment,
                                                   @RequestParam("patientId") Long patientId){
        try {
            PatientAppointment appointment = appointmentService.addAppointment(patientAppointment,patientId);
            return ResponseHandler.generateResponse("success", HttpStatus.OK, appointment);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Object> updateAppointment(@RequestBody PatientAppointment patientAppointment){
        try {
            appointmentService.updateAppointment(patientAppointment);
            return ResponseHandler.generateResponse("Appointment details updated", HttpStatus.OK, null);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteAppointment(@RequestParam("appointmentId") Long appointmentId){
        try {
            appointmentService.deleteAppointment(appointmentId);
            return ResponseHandler.generateResponse("Appointment deleted", HttpStatus.OK, null);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
}
