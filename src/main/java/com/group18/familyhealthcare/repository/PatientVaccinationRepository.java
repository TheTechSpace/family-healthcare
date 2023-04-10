package com.group18.familyhealthcare.repository;

import com.group18.familyhealthcare.entity.Patient;
import com.group18.familyhealthcare.entity.PatientVaccination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientVaccinationRepository extends JpaRepository<PatientVaccination, Long> {
    List<PatientVaccination> findPatientVaccinationByPatient(Long patient);
}
