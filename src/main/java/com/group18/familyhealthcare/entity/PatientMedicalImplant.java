package com.group18.familyhealthcare.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientMedicalImplant {
    @Id
    @SequenceGenerator(
            name = "implant_sequence",
            sequenceName = "implant_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "implant_sequence"
    )
    private Long medicalImplantId;
    private String title;
    private String type;
    private String description;
    private String notes;
    private String file;
    private String dateTime;
    private Long patient;
}
