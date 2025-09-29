package com.rakib.collegeERPsystem.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "faculty_course_assignments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacultyCourseAssignment extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id", referencedColumnName = "id", nullable = false)
    private Faculty faculty;

    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;


    @Column(nullable = false)
    private String semester;

    @Column(name = "academic_year", nullable = false)
    private String academicYear;
}