package com.group18.familyhealthcare.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientMedication {
    @Id
    @SequenceGenerator(
            name = "medication_sequence",
            sequenceName = "medication_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "medication_sequence"
    )
    private Long medicationId;
    private String medicineName;
    private String diseaseInformation;
    private String dosage;
    private LocalDate expiryDate;
    private String intervalType;
    private String intervalTime;
    private LocalDate startDate;
    private String notes;
    private String reminder;
    private String fileUrl;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(
//            name = "patient_patient_id",
//            referencedColumnName = "patientId"
//    )
//    private Patient patient;
    private Long patient;
}
