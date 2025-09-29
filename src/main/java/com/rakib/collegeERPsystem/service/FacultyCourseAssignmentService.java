package com.rakib.collegeERPsystem.service;
import com.rakib.collegeERPsystem.dtos.FacultyCourseAssignmentDTO;
import com.rakib.collegeERPsystem.entity.FacultyCourseAssignment;
import com.rakib.collegeERPsystem.repository.FacultyCourseAssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FacultyCourseAssignmentService {

    private final FacultyCourseAssignmentRepository repository;

    // Convert entity to DTO
    private FacultyCourseAssignmentDTO mapToDTO(FacultyCourseAssignment assignment) {
        return FacultyCourseAssignmentDTO.builder()
                .id(assignment.getId())
                .facultyId(assignment.getFaculty().getId())
                .facultyName(assignment.getFaculty().getName()) // Assuming Faculty has 'name' field
                .courseId(assignment.getCourse().getId())
                .courseName(assignment.getCourse().getCourseName()) // Assuming Course has 'courseName'
                .semester(assignment.getSemester())
                .academicYear(assignment.getAcademicYear())
                .build();
    }

    // Get all assignments
    public List<FacultyCourseAssignmentDTO> getAllAssignments() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Get assignments by faculty
    public List<FacultyCourseAssignmentDTO> getAssignmentsByFaculty(Long facultyId) {
        return repository.findByFacultyId(facultyId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Get assignments by course
    public List<FacultyCourseAssignmentDTO> getAssignmentsByCourse(Long courseId) {
        return repository.findByCourseId(courseId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Get assignments by faculty, semester, and academic year
    public List<FacultyCourseAssignmentDTO> getAssignmentsByFacultySemesterYear(Long facultyId, String semester, String academicYear) {
        return repository.findByFacultyIdAndSemesterAndAcademicYear(facultyId, semester, academicYear)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Create or update assignment
    public FacultyCourseAssignmentDTO saveAssignment(FacultyCourseAssignment assignment) {
        FacultyCourseAssignment saved = repository.save(assignment);
        return mapToDTO(saved);
    }

    // Delete assignment
    public void deleteAssignment(Long id) {
        repository.deleteById(id);
    }
}
