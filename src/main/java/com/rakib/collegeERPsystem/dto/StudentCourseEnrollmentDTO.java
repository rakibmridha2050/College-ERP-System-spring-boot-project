package com.rakib.collegeERPsystem.dto;

import com.rakib.collegeERPsystem.enums.EnrollmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentCourseEnrollmentDTO {

    private Long enrollmentId;

    private Long studentId;
    private String studentName;

    private Long courseId;
    private String courseName;

    private String semester;
    private String academicYear;
    private EnrollmentStatus status;
}
