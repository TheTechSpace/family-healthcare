package com.group18.familyhealthcare.repository;

import com.group18.familyhealthcare.entity.PatientMedication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientMedicationRepository extends JpaRepository<PatientMedication, Long> {
    List<PatientMedication> findPatientMedicationByPatient(Long patientId);
}
