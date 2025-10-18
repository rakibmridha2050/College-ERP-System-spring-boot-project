package com.rakib.collegeERPsystem.controller;
import com.rakib.collegeERPsystem.dto.FacultyDTO;
import com.rakib.collegeERPsystem.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faculty")
@RequiredArgsConstructor
public class FacultyController {

    private final FacultyService facultyService;

    // Get all faculty
    @GetMapping
    public ResponseEntity<List<FacultyDTO>> getAllFaculty() {
        List<FacultyDTO> facultyList = facultyService.getAllFaculty();
        return ResponseEntity.ok(facultyList);
    }

    // Get faculty by ID
    @GetMapping("/{id}")
    public ResponseEntity<FacultyDTO> getFacultyById(@PathVariable Long id) {
        FacultyDTO faculty = facultyService.getFacultyById(id);
        return ResponseEntity.ok(faculty);
    }

    // Create or update faculty
    @PostMapping
    public ResponseEntity<FacultyDTO> saveFaculty(@RequestBody FacultyDTO facultyDTO) {
        FacultyDTO savedFaculty = facultyService.saveFaculty(facultyDTO);
        return ResponseEntity.ok(savedFaculty);
    }

    // Delete faculty
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.noContent().build();
    }

    // Optional: Get all faculty by department
    @GetMapping("/department/{deptId}")
    public ResponseEntity<List<FacultyDTO>> getFacultyByDepartment(@PathVariable Long deptId) {
        List<FacultyDTO> facultyList = facultyService.getAllFaculty()
                .stream()
                .filter(f -> f.getDepartmentId() != null && f.getDepartmentId().equals(deptId))
                .toList();
        return ResponseEntity.ok(facultyList);
    }
}
