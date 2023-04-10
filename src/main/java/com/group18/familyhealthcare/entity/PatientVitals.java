package com.group18.familyhealthcare.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientVitals {
    @Id
    @SequenceGenerator(
            name = "vital_sequence",
            sequenceName = "vital_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "vital_sequence"
    )
    private Long vitalId;
    private String vitalType;
    private String vitalDescription;
    private String vitalReading;
    private String vitalUnit;
    private String vitalDateTime;
    private Long patient;
}
