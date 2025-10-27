package com.rakib.collegeERPsystem.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTO {
    private Long id;

    @NotBlank(message = "Student ID is required")
    private String studentId;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @Pattern(regexp = "^(\\+88)?01[3-9]\\d{8}$", message = "Invalid Bangladeshi mobile number")
    private String phone;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dob;

    private String gender;

    @NotBlank(message = "Program is required")
    private String program;

    @Min(value = 1, message = "Semester must be at least 1")
    private int currentSemester;

    private String permanentAddress;
    private String presentAddress;
    private Boolean isActive;
    private Long departmentId;
    private Long classId;
    private Long sectionId;
    private List<Long> courseIds = new ArrayList<>();
}