package com.group18.familyhealthcare.repository;

import com.group18.familyhealthcare.entity.PatientAppointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientAppointmentRepository extends JpaRepository<PatientAppointment, Long> {
    List<PatientAppointment> findPatientAppointmentByPatient(Long patient);
}
