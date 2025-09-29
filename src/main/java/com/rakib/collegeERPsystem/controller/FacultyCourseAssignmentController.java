package com.rakib.collegeERPsystem.controller;
import com.rakib.collegeERPsystem.dtos.FacultyCourseAssignmentDTO;
import com.rakib.collegeERPsystem.entity.FacultyCourseAssignment;
import com.rakib.collegeERPsystem.service.FacultyCourseAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class FacultyCourseAssignmentController {

    private final FacultyCourseAssignmentService service;

    // Get all assignments
    @GetMapping
    public ResponseEntity<List<FacultyCourseAssignmentDTO>> getAllAssignments() {
        return ResponseEntity.ok(service.getAllAssignments());
    }

    // Get assignments by faculty
    @GetMapping("/faculty/{facultyId}")
    public ResponseEntity<List<FacultyCourseAssignmentDTO>> getAssignmentsByFaculty(@PathVariable Long facultyId) {
        return ResponseEntity.ok(service.getAssignmentsByFaculty(facultyId));
    }

    // Get assignments by course
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<FacultyCourseAssignmentDTO>> getAssignmentsByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(service.getAssignmentsByCourse(courseId));
    }

    // Get assignments by faculty, semester, and academic year
    @GetMapping("/faculty/{facultyId}/semester/{semester}/year/{academicYear}")
    public ResponseEntity<List<FacultyCourseAssignmentDTO>> getAssignmentsByFacultySemesterYear(
            @PathVariable Long facultyId,
            @PathVariable String semester,
            @PathVariable String academicYear) {

        return ResponseEntity.ok(service.getAssignmentsByFacultySemesterYear(facultyId, semester, academicYear));
    }

    // Create or update an assignment
    @PostMapping
    public ResponseEntity<FacultyCourseAssignmentDTO> saveAssignment(@RequestBody FacultyCourseAssignment assignment) {
        return ResponseEntity.ok(service.saveAssignment(assignment));
    }

    // Delete an assignment
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long id) {
        service.deleteAssignment(id);
        return ResponseEntity.noContent().build();
    }

}
