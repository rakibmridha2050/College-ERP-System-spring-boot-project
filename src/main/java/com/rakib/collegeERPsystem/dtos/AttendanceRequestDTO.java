package com.rakib.collegeERPsystem.dtos;

import com.rakib.collegeERPsystem.enums.AttendanceStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AttendanceRequestDTO {

    private Long enrollmentId;   // StudentCourseEnrollment ID
    private Long facultyId;      // Faculty ID
    private Long classId;        // Optional
    private Long sectionId;      // Optional
    private LocalDateTime attendanceDate; // Optional, default to now
    private AttendanceStatus status;      // PRESENT, ABSENT, etc.
}
