package com.group18.familyhealthcare.service;

import com.group18.familyhealthcare.entity.Patient;
import com.group18.familyhealthcare.entity.PatientMedicalImplant;
import com.group18.familyhealthcare.entity.PatientMedicalReport;
import com.group18.familyhealthcare.repository.PatientImplantRepository;
import com.group18.familyhealthcare.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PatientMedicalImplantService {
    private final PatientImplantRepository implantRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public PatientMedicalImplantService(PatientImplantRepository implantRepository, PatientRepository patientRepository) {
        this.implantRepository = implantRepository;
        this.patientRepository = patientRepository;
    }

    public List<PatientMedicalImplant> getMedicalImplant(Long patientId){
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if (patientOptional.isPresent()){
            return implantRepository.findPatientMedicalImplantByPatient(patientOptional.get().getPatientId());
        }else {
            throw new IllegalStateException("Patient does not exist");
        }
    }

    public PatientMedicalImplant addMedicalImplant(PatientMedicalImplant patientMedicalImplant, Long patientId){
        Patient patient = patientRepository.findById(patientId).orElseThrow(
                () -> new IllegalStateException("Patient does not exist"));
        patientMedicalImplant.setPatient(patient.getPatientId());
        return implantRepository.save(patientMedicalImplant);
    }

    @Transactional
    public void updateMedicalImplant(PatientMedicalImplant patientMedicalImplant){
        PatientMedicalImplant medicalImplant = implantRepository.findById(patientMedicalImplant.getMedicalImplantId()).orElseThrow(
                () -> new IllegalStateException("No corresponding medical implant found"));

        medicalImplant.setTitle(patientMedicalImplant.getTitle());
        medicalImplant.setDescription(patientMedicalImplant.getDescription());
        medicalImplant.setNotes(patientMedicalImplant.getNotes());
        medicalImplant.setType(patientMedicalImplant.getType());
        medicalImplant.setFile(patientMedicalImplant.getFile());
        medicalImplant.setDateTime(patientMedicalImplant.getDateTime());
    }

    public void deleteMedicalImplant(Long medicalImplantId){
        PatientMedicalImplant medicalImplant = implantRepository.findById(medicalImplantId).orElseThrow(
                () -> new IllegalStateException("Medical implant data does not exist"));
        medicalImplant.setPatient(null);
        implantRepository.delete(medicalImplant);
    }
}
