package com.group18.familyhealthcare.repository;

import com.group18.familyhealthcare.entity.Patient;
import com.group18.familyhealthcare.entity.PatientMedicalReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientMedicalReportRepository extends JpaRepository<PatientMedicalReport, Long> {
    List<PatientMedicalReport> findPatientMedicalReportByPatient(Long patient);
}
