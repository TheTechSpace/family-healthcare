package com.group18.familyhealthcare.repository;

import com.group18.familyhealthcare.entity.Patient;
import com.group18.familyhealthcare.entity.PatientVitals;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientVitalRepository extends JpaRepository<PatientVitals, Long> {
    List<PatientVitals> findPatientVitalsByPatient(Long patient);
}
