package com.rakib.collegeERPsystem.entity;



import com.rakib.collegeERPsystem.enums.AttendanceStatus;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "attendances")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long enrollmentId;
    private Long facultyId;
    private Long classId;
    private Long sectionId;

    private LocalDate attendanceDate;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;

    // ✅ Constructors
    public Attendance() {}

    public Attendance(Long enrollmentId, Long facultyId, Long classId, Long sectionId, LocalDate attendanceDate, AttendanceStatus status) {
        this.enrollmentId = enrollmentId;
        this.facultyId = facultyId;
        this.classId = classId;
        this.sectionId = sectionId;
        this.attendanceDate = attendanceDate;
        this.status = status;
    }

    // ✅ Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getEnrollmentId() { return enrollmentId; }
    public void setEnrollmentId(Long enrollmentId) { this.enrollmentId = enrollmentId; }

    public Long getFacultyId() { return facultyId; }
    public void setFacultyId(Long facultyId) { this.facultyId = facultyId; }

    public Long getClassId() { return classId; }
    public void setClassId(Long classId) { this.classId = classId; }

    public Long getSectionId() { return sectionId; }
    public void setSectionId(Long sectionId) { this.sectionId = sectionId; }

    public LocalDate getAttendanceDate() { return attendanceDate; }
    public void setAttendanceDate(LocalDate attendanceDate) { this.attendanceDate = attendanceDate; }

    public AttendanceStatus getStatus() { return status; }
    public void setStatus(AttendanceStatus status) { this.status = status; }
}
