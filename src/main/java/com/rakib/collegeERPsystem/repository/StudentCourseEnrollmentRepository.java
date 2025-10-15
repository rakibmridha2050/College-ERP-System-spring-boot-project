package com.rakib.collegeERPsystem.repository;

import com.rakib.collegeERPsystem.entity.StudentCourseEnrollment;
import com.rakib.collegeERPsystem.entity.Student;
import com.rakib.collegeERPsystem.entity.Course;

import com.rakib.collegeERPsystem.enums.EnrollmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentCourseEnrollmentRepository extends JpaRepository<StudentCourseEnrollment, Long> {

    // ✅ Find all enrollments of a student
    List<StudentCourseEnrollment> findByStudent(Student student);

    // ✅ Find all students enrolled in a specific course
    List<StudentCourseEnrollment> findByCourse(Course course);

    // ✅ Find by student and course (to prevent duplicate enrollment)
    Optional<StudentCourseEnrollment> findByStudentAndCourse(Student student, Course course);

    // ✅ Find by academic year
    List<StudentCourseEnrollment> findByAcademicYear(String academicYear);

    // ✅ Find by status (Active / Completed / Dropped)
    List<StudentCourseEnrollment> findByStatus(EnrollmentStatus status);
}
