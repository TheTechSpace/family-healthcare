package com.group18.familyhealthcare.repository;

import com.group18.familyhealthcare.entity.Patient;
import com.group18.familyhealthcare.entity.PatientMedicalImplant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientImplantRepository extends JpaRepository<PatientMedicalImplant, Long> {
    List<PatientMedicalImplant> findPatientMedicalImplantByPatient(Long patient);
}
