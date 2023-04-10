package com.group18.familyhealthcare.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientEmergencyCard {
    @Id
    @SequenceGenerator(
            name = "emergency_sequence",
            sequenceName = "emergency_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "emergency_sequence"
    )
    private Long emergencyCardId;
    private String title;
    private String policyNumber;
    private String insuranceProvider;
    private String allergies;
    private String currentMedication;
    private String notes;
    private String emergencyContact;
    private Long patient;
}
