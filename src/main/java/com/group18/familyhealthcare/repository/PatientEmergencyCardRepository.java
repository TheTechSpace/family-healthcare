package com.group18.familyhealthcare.repository;

import com.group18.familyhealthcare.entity.Patient;
import com.group18.familyhealthcare.entity.PatientEmergencyCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientEmergencyCardRepository extends JpaRepository<PatientEmergencyCard, Long> {
    List<PatientEmergencyCard> findPatientEmergencyCardByPatient(Long patient);
}
