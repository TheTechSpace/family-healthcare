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
public class PatientVaccination {
    @Id
    @SequenceGenerator(
            name = "vaccination_sequence",
            sequenceName = "vaccination_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "vaccination_sequence"
    )
    private Long vaccinationId;
    private String title;
    private String dosage;
    private String description;
    private LocalDate nextDosage;
    private String fileReport;
    private String reminder;
    private String dateTime;
    private Long patient;
}
