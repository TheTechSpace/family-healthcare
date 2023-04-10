package com.group18.familyhealthcare.controller;

import com.group18.familyhealthcare.entity.PatientAppointment;
import com.group18.familyhealthcare.utility.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(path = "/api/v1")
public class HomeController {
    @GetMapping(value = "/")
    public ResponseEntity<Object> home(){
        Map<String, ArrayList<String>> res = new HashMap<>();
        res.put("account ->", new ArrayList<>(Arrays.asList("/list", "/create", "/login")));
        res.put("Patient ->", new ArrayList<>(Arrays.asList("/list", "/add", "/details", "/update", "/delete")));
        res.put("Patient_Appointments ->", new ArrayList<>(Arrays.asList("/list", "/add", "/details", "/update", "/delete")));
        res.put("Patient_Vitals ->", new ArrayList<>(Arrays.asList("/list", "/add", "/details", "/update", "/delete")));
        res.put("Patient_Medication ->", new ArrayList<>(Arrays.asList("/list", "/add", "/details", "/update", "/delete")));
        res.put("Patient_Medical_Report ->", new ArrayList<>(Arrays.asList("/list", "/add", "/details", "/update", "/delete")));
        res.put("Patient_Medical_Implant ->", new ArrayList<>(Arrays.asList("/list", "/add", "/details", "/update", "/delete")));
        res.put("Patient_Vaccination ->", new ArrayList<>(Arrays.asList("/list", "/add", "/details", "/update", "/delete")));
        res.put("Patient_Emergency_Card ->", new ArrayList<>(Arrays.asList("/list", "/add", "/details", "/update", "/delete")));

        try {
            return ResponseHandler.generateResponse("success", HttpStatus.OK, res);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
}
