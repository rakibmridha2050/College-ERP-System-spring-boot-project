package com.rakib.collegeERPsystem.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDTO {
    private Long id;
    private String courseCode;
    private String courseName;
    private Integer credits;
    private Long departmentId;
    private String departmentName;

    // Optional: Include counts or basic info about relationships
    private Integer subjectCount;
    private Integer studentCount;
    private Integer facultyCount;
    private Integer semesterCount;

    // If you want to include specific details
    @Builder.Default
    private List<Long> facultyIds = new ArrayList<>();
    @Builder.Default
    private List<Long> studentIds = new ArrayList<>();
}