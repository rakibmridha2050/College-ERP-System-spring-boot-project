package com.rakib.collegeERPsystem.dto;

import com.rakib.collegeERPsystem.enums.AttendanceStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AttendanceRequestDTO {
    private Long enrollmentId;
    private Long studentId;
    private Long facultyId;
    private Long classId;
    private Long sectionId;
    private LocalDate attendanceDate;
    private AttendanceStatus status;

    // Getters & Setters

}
