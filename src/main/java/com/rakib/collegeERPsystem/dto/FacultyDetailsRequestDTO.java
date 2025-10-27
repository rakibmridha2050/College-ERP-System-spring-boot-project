package com.rakib.collegeERPsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacultyDetailsRequestDTO {
    private Long facultyId;
    private String employeeId;
    private LocalDate dateOfBirth;
    private String gender;
    private String bloodGroup;
    private String nationalId;
    private String passportNumber;

    // Address fields
    private String streetAddress;
    private String city;
    private String state;
    private String country;
    private String postalCode;

    // Emergency contact
    private String emergencyContact;
    private String emergencyContactRelationship;
    private String emergencyContactPhone;

    // Bank details
    private String bankAccountNumber;
    private String bankName;
    private String bankBranch;

    // Professional details
    private LocalDate joiningDate;
    private String qualification;
    private String specialization;
    private Integer experienceYears;
    private String salaryScale;
    private Double currentSalary;

    // Personal details
    private String maritalStatus;
    private String spouseName;

    // Documents
    private String profilePictureUrl;
    private String cvDocumentUrl;
    private String otherDocumentsUrls;

    // Additional information
    private String bio;
    private String researchInterests;
    private String publications;
    private String awardsAchievements;
}