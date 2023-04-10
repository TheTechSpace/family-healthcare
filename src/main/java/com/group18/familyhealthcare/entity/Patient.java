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
public class Patient {

    @Id
    @SequenceGenerator(
            name = "patient_sequence",
            sequenceName = "patient_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_sequence"
    )
    private Long patientId;
    private String fullName;
    private String address;
    private String phoneNumber;
    private String dob;
    private String gender;
    private String bloodGroup;
    private Double weight;
    private Double height;
    private String weightUnit;
    private String heightUnit;
    private String insuranceNumber;
    private String medicalCondition;
    @Lob
    private String profileImage;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(
//            name = "user_account_account_id",
//            referencedColumnName = "accountId"
//    )
    private Long userAccount;
}
