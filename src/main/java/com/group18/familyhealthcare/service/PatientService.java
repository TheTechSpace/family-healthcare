package com.group18.familyhealthcare.service;

import com.group18.familyhealthcare.entity.Patient;
import com.group18.familyhealthcare.entity.UserAccount;
import com.group18.familyhealthcare.repository.PatientRepository;
import com.group18.familyhealthcare.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final UserAccountRepository accountRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository, UserAccountRepository accountRepository) {
        this.patientRepository = patientRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public List<Patient> getAllPatient(Long accountId){
        UserAccount currentUser = accountRepository.findUserAccountByAccountId(accountId);
       return patientRepository.findPatientByUserAccount(currentUser.getAccountId());
    }

    @Transactional
    public Patient patientDetails(Long patientId){
        Patient patient = patientRepository.findPatientByPatientId(patientId);
        return patient;
    }

    public Object addPatient(Patient patient, Long accountId){
        HashMap<String, Object> data = new HashMap<>();
        Optional<Patient> patientExist = patientRepository
                .findPatientByInsuranceNumber(patient.getInsuranceNumber());
        UserAccount currentUser = accountRepository.findUserAccountByAccountId(accountId);
        if (patientExist.isPresent()){
            throw new IllegalStateException("Patient with insurance number already exist");
        }
        patient.setUserAccount(currentUser.getAccountId());
        Patient savedPatient = patientRepository.save(patient);
        data.put("patientId", savedPatient.getPatientId());
        data.put("name", savedPatient.getFullName());
        return data;
    }

    public void deletePatient(Long patientId){
        Optional<Patient> patientExist = patientRepository.findById(patientId);
        if (patientExist.isPresent()){
            patientExist.get().setUserAccount(null);
            patientRepository.deleteById(patientId);
        }else {
            throw new IllegalStateException("Patient not found");
        }
    }

    @Transactional
    public void updatePatient(Patient patient) {
        Patient patientExist = patientRepository.findById(patient.getPatientId()).orElseThrow(
                () -> new IllegalStateException("Patient does not exist")
        );
        patientExist.setFullName(patient.getFullName());
        patientExist.setDob(patient.getDob());
        patientExist.setGender(patient.getGender());
        patientExist.setAddress(patient.getAddress());
        patientExist.setHeight(patient.getHeight());
        patientExist.setBloodGroup(patient.getBloodGroup());
        patientExist.setMedicalCondition(patient.getMedicalCondition());
        patientExist.setInsuranceNumber(patient.getInsuranceNumber());
        patientExist.setPhoneNumber(patient.getPhoneNumber());
        patientExist.setProfileImage(patient.getProfileImage());

    }
}
