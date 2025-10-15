package com.rakib.collegeERPsystem.entity;

import com.rakib.collegeERPsystem.enums.EnrollmentStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "student_course_enrollments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentCourseEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollment_id")
    private Long enrollmentId;

    // Many enrollments belong to one student
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    // Many enrollments belong to one course
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(nullable = false)
    private String semester; // e.g., "Spring", "Fall", "Summer"

    @Column(name = "academic_year", nullable = false)
    private String academicYear; // e.g., "2024-2025"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnrollmentStatus status;

    @Column(name = "enrolled_at", updatable = false)
    private LocalDateTime enrolledAt = LocalDateTime.now();
}
