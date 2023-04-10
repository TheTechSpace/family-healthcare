package com.group18.familyhealthcare.service;

import com.group18.familyhealthcare.entity.Patient;
import com.group18.familyhealthcare.entity.PatientMedicalReport;
import com.group18.familyhealthcare.repository.PatientMedicalReportRepository;
import com.group18.familyhealthcare.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PatientMedicalReportService {
    private final PatientMedicalReportRepository medicalReportRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public PatientMedicalReportService(PatientMedicalReportRepository medicalReportRepository, PatientRepository patientRepository) {
        this.medicalReportRepository = medicalReportRepository;
        this.patientRepository = patientRepository;
    }

    public List<PatientMedicalReport> getMedicalReport(Long patientId){
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if (patientOptional.isPresent()){
            return medicalReportRepository.findPatientMedicalReportByPatient(patientOptional.get().getPatientId());
        }else {
            throw new IllegalStateException("Patient does not exist");
        }
    }

    public PatientMedicalReport addMedicalReport(PatientMedicalReport patientMedicalReport, Long patientId){
        Patient patient = patientRepository.findById(patientId).orElseThrow(
                () -> new IllegalStateException("Patient does not exist"));
        patientMedicalReport.setPatient(patient.getPatientId());
        return medicalReportRepository.save(patientMedicalReport);
    }

    @Transactional
    public void updateMedicalReport(PatientMedicalReport patientMedicalReport){
        PatientMedicalReport medicalReport = medicalReportRepository.findById(patientMedicalReport.getMedicalReportId()).orElseThrow(
                () -> new IllegalStateException("No corresponding medical report found"));

        medicalReport.setTitle(patientMedicalReport.getTitle());
        medicalReport.setDescription(patientMedicalReport.getDescription());
        medicalReport.setNotes(patientMedicalReport.getNotes());
        medicalReport.setType(patientMedicalReport.getType());
        medicalReport.setFile(patientMedicalReport.getFile());
        medicalReport.setDateTime(patientMedicalReport.getDateTime());
    }

    public void deleteMedicalReport(Long medicalReportId){
        PatientMedicalReport medicalReport = medicalReportRepository.findById(medicalReportId).orElseThrow(
                () -> new IllegalStateException("Medical report data does not exist"));
        medicalReport.setPatient(null);
        medicalReportRepository.delete(medicalReport);
    }

}
