package com.group18.familyhealthcare.service;

import com.group18.familyhealthcare.entity.Patient;
import com.group18.familyhealthcare.entity.PatientAppointment;
import com.group18.familyhealthcare.entity.PatientVaccination;
import com.group18.familyhealthcare.repository.PatientRepository;
import com.group18.familyhealthcare.repository.PatientVaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PatientVaccinationService {
    private final PatientVaccinationRepository vaccinationRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public PatientVaccinationService(PatientVaccinationRepository vaccinationRepository, PatientRepository patientRepository) {
        this.vaccinationRepository = vaccinationRepository;
        this.patientRepository = patientRepository;
    }

    public List<PatientVaccination> getVaccination(Long patientId){
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if (patientOptional.isPresent()){
            return vaccinationRepository.findPatientVaccinationByPatient((patientOptional.get().getPatientId()));
        }else {
            throw new IllegalStateException("Patient does not exist");
        }
    }

    public PatientVaccination addVaccination(PatientVaccination patientVaccination, Long patientId){
        Patient patient = patientRepository.findById(patientId).orElseThrow(
                () -> new IllegalStateException("Patient does not exist"));
        patientVaccination.setPatient(patient.getPatientId());
        return vaccinationRepository.save(patientVaccination);
    }

    @Transactional
    public void updateVaccination(PatientVaccination patientVaccination){
        PatientVaccination vaccination = vaccinationRepository.findById(patientVaccination.getVaccinationId())
                .orElseThrow(() -> new IllegalStateException("No corresponding vaccination found"));

        vaccination.setDosage(patientVaccination.getDosage());
        vaccination.setDescription(patientVaccination.getDescription());
        vaccination.setTitle(patientVaccination.getTitle());
        vaccination.setReminder(patientVaccination.getReminder());
        vaccination.setNextDosage(patientVaccination.getNextDosage());
        vaccination.setFileReport(patientVaccination.getFileReport());
    }

    public void deleteVaccination(Long vaccinationId){
        PatientVaccination vaccination = vaccinationRepository.findById(vaccinationId).orElseThrow(
                () -> new IllegalStateException("Vaccination does not exist"));
        vaccination.setPatient(null);
        vaccinationRepository.delete(vaccination);
    }
}
