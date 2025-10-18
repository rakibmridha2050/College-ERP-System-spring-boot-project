package com.rakib.collegeERPsystem.service;



import com.rakib.collegeERPsystem.dto.StudentCourseEnrollmentDTO;
import com.rakib.collegeERPsystem.entity.Course;
import com.rakib.collegeERPsystem.entity.Student;
import com.rakib.collegeERPsystem.entity.StudentCourseEnrollment;
import com.rakib.collegeERPsystem.enums.EnrollmentStatus;
import com.rakib.collegeERPsystem.repository.CourseRepository;
import com.rakib.collegeERPsystem.repository.StudentCourseEnrollmentRepository;
import com.rakib.collegeERPsystem.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentCourseEnrollmentService {

    private final StudentCourseEnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    //  Enroll a student in a course
    public StudentCourseEnrollmentDTO enrollStudent(StudentCourseEnrollmentDTO dto) {
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // Prevent duplicate enrollment
        enrollmentRepository.findByStudentAndCourse(student, course)
                .ifPresent(e -> { throw new RuntimeException("Student already enrolled in this course"); });

        StudentCourseEnrollment enrollment = StudentCourseEnrollment.builder()
                .student(student)
                .course(course)
                .semester(dto.getSemester())
                .academicYear(dto.getAcademicYear())
                .status(dto.getStatus() != null ? dto.getStatus() : EnrollmentStatus.ACTIVE)
                .build();

        StudentCourseEnrollment saved = enrollmentRepository.save(enrollment);
        return mapToDTO(saved);
    }

    //  Get all enrollments
    public List<StudentCourseEnrollmentDTO> getAllEnrollments() {
        return enrollmentRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    //  Get enrollments by student
    public List<StudentCourseEnrollmentDTO> getEnrollmentsByStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return enrollmentRepository.findByStudent(student)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    //  Get enrollments by course
    public List<StudentCourseEnrollmentDTO> getEnrollmentsByCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return enrollmentRepository.findByCourse(course)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    //  Update enrollment status
    public StudentCourseEnrollmentDTO updateStatus(Long enrollmentId, EnrollmentStatus status) {
        StudentCourseEnrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
        enrollment.setStatus(status);
        return mapToDTO(enrollmentRepository.save(enrollment));
    }

    //  Delete enrollment
    public void deleteEnrollment(Long enrollmentId) {
        if (!enrollmentRepository.existsById(enrollmentId)) {
            throw new RuntimeException("Enrollment not found");
        }
        enrollmentRepository.deleteById(enrollmentId);
    }

    //  Mapping helper
    private StudentCourseEnrollmentDTO mapToDTO(StudentCourseEnrollment e) {
        return StudentCourseEnrollmentDTO.builder()
                .enrollmentId(e.getEnrollmentId())
                .studentId(e.getStudent().getId())
                .studentName(e.getStudent().getName())
                .courseId(e.getCourse().getId())
                .courseName(e.getCourse().getCourseName())
                .semester(e.getSemester())
                .academicYear(e.getAcademicYear())
                .status(e.getStatus())
                .build();
    }
}