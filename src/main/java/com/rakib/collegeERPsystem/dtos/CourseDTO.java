package com.rakib.collegeERPsystem.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseDTO {
    private Long courseId;
    private String courseCode;
    private String courseName;
    private Integer credits;
    private Long deptId;       // department id
    private String deptName;   // optional, for convenience
}
