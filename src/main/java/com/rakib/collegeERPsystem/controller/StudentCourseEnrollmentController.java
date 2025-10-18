package com.rakib.collegeERPsystem.controller;



import com.rakib.collegeERPsystem.dto.StudentCourseEnrollmentDTO;
import com.rakib.collegeERPsystem.enums.EnrollmentStatus;
import com.rakib.collegeERPsystem.service.StudentCourseEnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class StudentCourseEnrollmentController {

    private final StudentCourseEnrollmentService enrollmentService;

    /**
     * ✅ Enroll a student in a course
     */
    @PostMapping
    public ResponseEntity<StudentCourseEnrollmentDTO> enrollStudent(
            @RequestBody StudentCourseEnrollmentDTO dto) {
        return ResponseEntity.ok(enrollmentService.enrollStudent(dto));
    }

    /**
     * ✅ Get all enrollments
     */
    @GetMapping
    public ResponseEntity<List<StudentCourseEnrollmentDTO>> getAllEnrollments() {
        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }

    /**
     * ✅ Get enrollments by student
     */
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<StudentCourseEnrollmentDTO>> getEnrollmentsByStudent(
            @PathVariable Long studentId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByStudent(studentId));
    }

    /**
     * ✅ Get enrollments by course
     */
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<StudentCourseEnrollmentDTO>> getEnrollmentsByCourse(
            @PathVariable Long courseId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByCourse(courseId));
    }

    /**
     * ✅ Update enrollment status
     */
    @PutMapping("/{enrollmentId}/status")
    public ResponseEntity<StudentCourseEnrollmentDTO> updateStatus(
            @PathVariable Long enrollmentId,
            @RequestParam EnrollmentStatus status) {
        return ResponseEntity.ok(enrollmentService.updateStatus(enrollmentId, status));
    }

    /**
     * ✅ Delete enrollment
     */
    @DeleteMapping("/{enrollmentId}")
    public ResponseEntity<String> deleteEnrollment(@PathVariable Long enrollmentId) {
        enrollmentService.deleteEnrollment(enrollmentId);
        return ResponseEntity.ok("Enrollment deleted successfully");
    }
}
