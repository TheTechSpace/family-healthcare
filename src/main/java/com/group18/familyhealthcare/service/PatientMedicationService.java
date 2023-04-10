package com.group18.familyhealthcare.service;

import com.group18.familyhealthcare.entity.Patient;
import com.group18.familyhealthcare.entity.PatientMedication;
import com.group18.familyhealthcare.repository.PatientMedicationRepository;
import com.group18.familyhealthcare.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PatientMedicationService {
    private final PatientMedicationRepository medicationRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public PatientMedicationService(PatientMedicationRepository medicationRepository, PatientRepository patientRepository) {
        this.medicationRepository = medicationRepository;
        this.patientRepository = patientRepository;
    }

    public List<PatientMedication> getMedication(Long patientId){
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if (patientOptional.isPresent()){
            return medicationRepository.findPatientMedicationByPatient(patientId);
        }else {
            throw new IllegalStateException("Patient does not exist");
        }
    }

    public PatientMedication addMedication(PatientMedication patientMedication, Long patientId){
        Patient patient = patientRepository.findById(patientId).orElseThrow(
                () -> new IllegalStateException("Patient does not exist"));
        patientMedication.setPatient(patient.getPatientId());
        return medicationRepository.save(patientMedication);
    }

    @Transactional
    public void updateMedication(PatientMedication patientMedication){
        PatientMedication medication = medicationRepository.findById(patientMedication.getMedicationId()).orElseThrow(
                () -> new IllegalStateException("No corresponding medication found"));

        medication.setDosage(patientMedication.getDosage());
        medication.setNotes(patientMedication.getNotes());
        medication.setExpiryDate(patientMedication.getExpiryDate());
        medication.setReminder(patientMedication.getReminder());
        medication.setIntervalTime(patientMedication.getIntervalTime());
        medication.setIntervalType(patientMedication.getIntervalType());
        medication.setDiseaseInformation(patientMedication.getDiseaseInformation());
        medication.setReminder(patientMedication.getReminder());
        medication.setStartDate(patientMedication.getStartDate());
        medication.setMedicineName(patientMedication.getMedicineName());
    }

    public void deleteMedication(Long medicationId){
        PatientMedication medication = medicationRepository.findById(medicationId).orElseThrow(
                () -> new IllegalStateException("Medication data does not exist"));
        medication.setPatient(null);
        medicationRepository.delete(medication);
    }

}
