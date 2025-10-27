package com.rakib.collegeERPsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResponseDTO {
    private Long id;
    private String studentId;
    private String name;
    private String email;
    private String phone;
    private LocalDate dob;
    private String gender;
    private String program;
    private int currentSemester;
    private String permanentAddress;
    private String presentAddress;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Related entity details
    private String departmentName;
    private String className;
    private String sectionName;
    private List<String> courseNames = new ArrayList<>();
}