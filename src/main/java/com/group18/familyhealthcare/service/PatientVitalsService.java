package com.group18.familyhealthcare.service;

import com.group18.familyhealthcare.entity.Patient;
import com.group18.familyhealthcare.entity.PatientMedication;
import com.group18.familyhealthcare.entity.PatientVitals;
import com.group18.familyhealthcare.repository.PatientRepository;
import com.group18.familyhealthcare.repository.PatientVitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PatientVitalsService {
    private final PatientVitalRepository vitalRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public PatientVitalsService(PatientVitalRepository vitalRepository, PatientRepository patientRepository) {
        this.vitalRepository = vitalRepository;
        this.patientRepository = patientRepository;
    }

    public List<PatientVitals> getVitals(Long patientId){
        Patient patient = patientRepository.findById(patientId).orElseThrow(
                () -> new IllegalStateException("Patient does not exist"));
        return vitalRepository.findPatientVitalsByPatient(patient.getPatientId());
    }

    public PatientVitals addVitals(PatientVitals vitals, Long patientId){
        Patient patient = patientRepository.findById(patientId).orElseThrow(
                () -> new IllegalStateException("Patient does not exist"));
        vitals.setPatient(patient.getPatientId());
        return vitalRepository.save(vitals);
    }

    @Transactional
    public void updateVitals(PatientVitals patientVitals){
        PatientVitals vitals = vitalRepository.findById(patientVitals.getVitalId()).orElseThrow(
                () -> new IllegalStateException("No corresponding vitals found"));
        vitals.setVitalDescription(patientVitals.getVitalDescription());
        vitals.setVitalReading(patientVitals.getVitalReading());
        vitals.setVitalUnit(patientVitals.getVitalUnit());
        vitals.setVitalDateTime(patientVitals.getVitalDateTime());
    }

    public void deleteVitals(Long vitalsId){
        PatientVitals vitals = vitalRepository.findById(vitalsId).orElseThrow(
                () -> new IllegalStateException("Vitals data does not exist"));
        vitals.setPatient(null);
        vitalRepository.delete(vitals);
    }
}
