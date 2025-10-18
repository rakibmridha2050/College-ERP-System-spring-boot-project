package com.rakib.collegeERPsystem.dto;

import com.rakib.collegeERPsystem.enums.AttendanceStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AttendanceResponseDTO {

    private Long id;                // Attendance ID
    private Long enrollmentId;      // StudentCourseEnrollment ID
    private String studentName;     // Student name
    private Long facultyId;
    private String facultyName;
    private Long classId;
    private String className;
    private Long sectionId;
    private String sectionName;
    private LocalDateTime attendanceDate;
    private AttendanceStatus status;
}
