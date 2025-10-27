package com.rakib.collegeERPsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "faculty_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacultyDetails extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id", nullable = false, unique = true)
    private Faculty faculty;

    @Column(name = "employee_id", unique = true, nullable = false)
    private String employeeId;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "blood_group")
    private String bloodGroup;

    @Column(name = "national_id", unique = true)
    private String nationalId;

    @Column(name = "passport_number", unique = true)
    private String passportNumber;

    @Embedded
    private Address address;

    @Column(name = "emergency_contact")
    private String emergencyContact;

    @Column(name = "emergency_contact_relationship")
    private String emergencyContactRelationship;

    @Column(name = "emergency_contact_phone")
    private String emergencyContactPhone;

    @Column(name = "bank_account_number")
    private String bankAccountNumber;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "bank_branch")
    private String bankBranch;

    @Column(name = "joining_date", nullable = false)
    private LocalDate joiningDate;

    @Column(name = "qualification")
    private String qualification;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "experience_years")
    private Integer experienceYears;

    @Column(name = "salary_scale")
    private String salaryScale;

    @Column(name = "current_salary")
    private Double currentSalary;

    @Column(name = "marital_status")
    private String maritalStatus;

    @Column(name = "spouse_name")
    private String spouseName;

    @Column(name = "profile_picture_url")
    private String profilePictureUrl;

    @Column(name = "cv_document_url")
    private String cvDocumentUrl;

    @Column(name = "other_documents_urls")
    private String otherDocumentsUrls; // JSON string or comma-separated URLs

    @Lob
    @Column(name = "bio", length = 1000)
    private String bio;

    @Column(name = "research_interests")
    private String researchInterests;

    @Column(name = "publications")
    private String publications; // JSON string or comma-separated publication titles

    @Column(name = "awards_achievements")
    private String awardsAchievements; // JSON string or comma-separated awards
}