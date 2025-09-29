package com.rakib.collegeERPsystem.service;
import com.rakib.collegeERPsystem.dtos.FacultyDTO;
import com.rakib.collegeERPsystem.entity.Department;
import com.rakib.collegeERPsystem.entity.Faculty;
import com.rakib.collegeERPsystem.entity.User;
import com.rakib.collegeERPsystem.repository.DepartmentRepository;
import com.rakib.collegeERPsystem.repository.FacultyRepository;
import com.rakib.collegeERPsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;

    // Get all faculty
    public List<FacultyDTO> getAllFaculty() {
        return facultyRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Get faculty by ID
    public FacultyDTO getFacultyById(Long id) {
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faculty not found with id: " + id));
        return mapToDTO(faculty);
    }

    // Create or update faculty
    public FacultyDTO saveFaculty(FacultyDTO dto) {
        Faculty faculty = new Faculty();
        if (dto.getId() != null) {
            faculty = facultyRepository.findById(dto.getId())
                    .orElse(new Faculty());
        }

        // Set fields
        faculty.setName(dto.getName());
        faculty.setEmail(dto.getEmail());
        faculty.setPhone(dto.getPhone());
        faculty.setDesignation(dto.getDesignation());

        // Set User
        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getUserId()));
            faculty.setUser(user);
        }

        // Set Department
        if (dto.getDepartmentId() != null) {
            Department department = departmentRepository.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found with id: " + dto.getDepartmentId()));
            faculty.setDepartment(department);
        }

        Faculty saved = facultyRepository.save(faculty);
        return mapToDTO(saved);
    }

    // Delete faculty
    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    // Map entity to DTO
    private FacultyDTO mapToDTO(Faculty faculty) {
        return FacultyDTO.builder()
                .id(faculty.getId())
                .name(faculty.getName())
                .email(faculty.getEmail())
                .phone(faculty.getPhone())
                .designation(faculty.getDesignation())
                .userId(faculty.getUser() != null ? faculty.getUser().getId() : null)
                .userName(faculty.getUser() != null ? faculty.getUser().getUsername() : null)
                .departmentId(faculty.getDepartment() != null ? faculty.getDepartment().getId() : null)
                .departmentName(faculty.getDepartment() != null ? faculty.getDepartment().getDeptName() : null)
                .build();
    }
}
