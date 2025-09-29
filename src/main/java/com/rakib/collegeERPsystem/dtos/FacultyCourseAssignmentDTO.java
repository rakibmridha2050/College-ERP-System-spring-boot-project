package com.rakib.collegeERPsystem.dtos;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FacultyCourseAssignmentDTO {

    private Long id;                 // from BaseEntity
    private Long facultyId;
    private String facultyName;      // optional, for convenience
    private Long courseId;
    private String courseName;       // optional
    private String semester;
    private String academicYear;
}
