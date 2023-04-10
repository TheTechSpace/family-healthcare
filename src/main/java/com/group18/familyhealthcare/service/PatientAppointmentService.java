package com.group18.familyhealthcare.service;

import com.group18.familyhealthcare.entity.Patient;
import com.group18.familyhealthcare.entity.PatientAppointment;
import com.group18.familyhealthcare.repository.PatientAppointmentRepository;
import com.group18.familyhealthcare.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PatientAppointmentService {
    private final PatientAppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public PatientAppointmentService(PatientAppointmentRepository appointmentRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
    }

    public List<PatientAppointment> getAppointment(Long patientId){
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if (patientOptional.isPresent()){
            return appointmentRepository.findPatientAppointmentByPatient(patientOptional.get().getPatientId());
        }else {
            throw new IllegalStateException("Patient does not exist");
        }
    }

    public PatientAppointment addAppointment(PatientAppointment patientAppointment, Long patientId){
        Patient patient = patientRepository.findById(patientId).orElseThrow(
                () -> new IllegalStateException("Patient does not exist"));
        patientAppointment.setPatient(patient.getPatientId());
        return appointmentRepository.save(patientAppointment);
    }

    @Transactional
    public void updateAppointment(PatientAppointment patientAppointment){
        PatientAppointment appointment = appointmentRepository.findById(patientAppointment.getAppointmentId())
                .orElseThrow(() -> new IllegalStateException("No corresponding appointment found"));

        appointment.setTitle(patientAppointment.getTitle());
        appointment.setNotes(patientAppointment.getNotes());
        appointment.setSymptoms(patientAppointment.getSymptoms());
        appointment.setReminder(patientAppointment.getReminder());
        appointment.setFileReference(patientAppointment.getFileReference());
        appointment.setDoctorName(patientAppointment.getDoctorName());
        appointment.setDateTime(patientAppointment.getDateTime());
    }

    public void deleteAppointment(Long appointmentId){
        PatientAppointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(
                () -> new IllegalStateException("Appointment does not exist"));
        appointment.setPatient(null);
        appointmentRepository.delete(appointment);
    }
}
