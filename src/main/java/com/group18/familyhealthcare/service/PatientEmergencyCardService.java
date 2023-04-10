package com.group18.familyhealthcare.service;

import com.group18.familyhealthcare.entity.Patient;
import com.group18.familyhealthcare.entity.PatientEmergencyCard;
import com.group18.familyhealthcare.repository.PatientEmergencyCardRepository;
import com.group18.familyhealthcare.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PatientEmergencyCardService {
    private final PatientEmergencyCardRepository emergencyCardRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public PatientEmergencyCardService(PatientEmergencyCardRepository emergencyCardRepository, PatientRepository patientRepository) {
        this.emergencyCardRepository = emergencyCardRepository;
        this.patientRepository = patientRepository;
    }

    public List<PatientEmergencyCard> getEmergencyCard(Long patientId){
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if (patientOptional.isPresent()){
            return emergencyCardRepository.findPatientEmergencyCardByPatient(patientOptional.get().getPatientId());
        }else {
            throw new IllegalStateException("Patient does not exist");
        }
    }

    public PatientEmergencyCard addEmergencyCard(PatientEmergencyCard patientEmergencyCard, Long patientId){
        Patient patient = patientRepository.findById(patientId).orElseThrow(
                () -> new IllegalStateException("Patient does not exist"));
        patientEmergencyCard.setPatient(patient.getPatientId());
        return emergencyCardRepository.save(patientEmergencyCard);
    }

    @Transactional
    public void updateEmergencyCard(PatientEmergencyCard patientEmergencyCard){
        PatientEmergencyCard emergencyCard = emergencyCardRepository.findById(patientEmergencyCard.getEmergencyCardId())
                .orElseThrow(() -> new IllegalStateException("No corresponding emergency card found"));

        emergencyCard.setTitle(patientEmergencyCard.getTitle());
        emergencyCard.setAllergies(patientEmergencyCard.getAllergies());
        emergencyCard.setNotes(patientEmergencyCard.getNotes());
        emergencyCard.setInsuranceProvider(patientEmergencyCard.getInsuranceProvider());
        emergencyCard.setPolicyNumber(patientEmergencyCard.getPolicyNumber());
        emergencyCard.setEmergencyContact(patientEmergencyCard.getEmergencyContact());
        emergencyCard.setCurrentMedication(patientEmergencyCard.getCurrentMedication());
    }

    public void deleteEmergencyCard(Long emergencyCardId){
        PatientEmergencyCard patientEmergencyCard = emergencyCardRepository.findById(emergencyCardId).orElseThrow(
                () -> new IllegalStateException("emergency card data does not exist"));
        patientEmergencyCard.setPatient(null);
        emergencyCardRepository.delete(patientEmergencyCard);
    }
}
