package com.group18.familyhealthcare.repository;

import com.group18.familyhealthcare.entity.Patient;
import com.group18.familyhealthcare.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findPatientByInsuranceNumber(String insuranceNumber);
    List<Patient> findPatientByUserAccount(Long userAccount);

    Patient findPatientByPatientId(Long patientId);

}
