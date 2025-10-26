package com.rakib.collegeERPsystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class CourseFacultyDTO {
    private Long courseId;
    private List<Long> facultyIds;
}