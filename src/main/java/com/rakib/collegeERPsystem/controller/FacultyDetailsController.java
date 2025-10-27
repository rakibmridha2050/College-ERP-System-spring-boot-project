package com.rakib.collegeERPsystem.controller;

import com.rakib.collegeERPsystem.dto.FacultyDetailsRequestDTO;
import com.rakib.collegeERPsystem.dto.FacultyDetailsResponseDTO;
import com.rakib.collegeERPsystem.service.FacultyDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faculty-details")
@RequiredArgsConstructor
public class FacultyDetailsController {

    private final FacultyDetailsService facultyDetailsService;

    @PostMapping
    public ResponseEntity<FacultyDetailsResponseDTO> createFacultyDetails(
            @Valid @RequestBody FacultyDetailsRequestDTO requestDTO) {
        FacultyDetailsResponseDTO responseDTO = facultyDetailsService.createFacultyDetails(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacultyDetailsResponseDTO> getFacultyDetailsById(@PathVariable Long id) {
        FacultyDetailsResponseDTO responseDTO = facultyDetailsService.getFacultyDetailsById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/faculty/{facultyId}")
    public ResponseEntity<FacultyDetailsResponseDTO> getFacultyDetailsByFacultyId(@PathVariable Long facultyId) {
        FacultyDetailsResponseDTO responseDTO = facultyDetailsService.getFacultyDetailsByFacultyId(facultyId);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<FacultyDetailsResponseDTO>> getAllFacultyDetails() {
        List<FacultyDetailsResponseDTO> responseDTOs = facultyDetailsService.getAllFacultyDetails();
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<FacultyDetailsResponseDTO>> getFacultyDetailsByDepartment(
            @PathVariable Long departmentId) {
        List<FacultyDetailsResponseDTO> responseDTOs = facultyDetailsService.getFacultyDetailsByDepartment(departmentId);
        return ResponseEntity.ok(responseDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacultyDetailsResponseDTO> updateFacultyDetails(
            @PathVariable Long id,
            @Valid @RequestBody FacultyDetailsRequestDTO requestDTO) {
        FacultyDetailsResponseDTO responseDTO = facultyDetailsService.updateFacultyDetails(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacultyDetails(@PathVariable Long id) {
        facultyDetailsService.deleteFacultyDetails(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/permanent/{id}")
    public ResponseEntity<Void> permanentDeleteFacultyDetails(@PathVariable Long id) {
        facultyDetailsService.permanentDeleteFacultyDetails(id);
        return ResponseEntity.noContent().build();
    }
}