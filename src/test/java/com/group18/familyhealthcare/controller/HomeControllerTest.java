package com.group18.familyhealthcare.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {
    @Test
    public void home_returns_expected_response() {
        // Arrange
        HomeController controller = new HomeController();
        Map<String, ArrayList<String>> expected = new HashMap<>();
        expected.put("account ->", new ArrayList<>(Arrays.asList("/list", "/create", "/login")));
        expected.put("Patient ->", new ArrayList<>(Arrays.asList("/list", "/add", "/details", "/update", "/delete")));
        expected.put("Patient_Appointments ->", new ArrayList<>(Arrays.asList("/list", "/add", "/details", "/update", "/delete")));
        expected.put("Patient_Vitals ->", new ArrayList<>(Arrays.asList("/list", "/add", "/details", "/update", "/delete")));
        expected.put("Patient_Medication ->", new ArrayList<>(Arrays.asList("/list", "/add", "/details", "/update", "/delete")));
        expected.put("Patient_Medical_Report ->", new ArrayList<>(Arrays.asList("/list", "/add", "/details", "/update", "/delete")));
        expected.put("Patient_Medical_Implant ->", new ArrayList<>(Arrays.asList("/list", "/add", "/details", "/update", "/delete")));
        expected.put("Patient_Vaccination ->", new ArrayList<>(Arrays.asList("/list", "/add", "/details", "/update", "/delete")));
        expected.put("Patient_Emergency_Card ->", new ArrayList<>(Arrays.asList("/list", "/add", "/details", "/update", "/delete")));

        // Act
        ResponseEntity<Object> response = controller.home();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
//        assertTrue(response.getBody() instanceof ResponseEntity<?>);
//        assertEquals(expected, response.getBody());
    }

}