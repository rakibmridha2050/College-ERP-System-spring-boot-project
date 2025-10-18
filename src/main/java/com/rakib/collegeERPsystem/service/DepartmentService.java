package com.rakib.collegeERPsystem.service;


import com.rakib.collegeERPsystem.dto.DepartmentDTO;
import com.rakib.collegeERPsystem.entity.Department;
import com.rakib.collegeERPsystem.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
//    private final FacultyRepository facultyRepository;

    // Convert Entity → DTO
    private DepartmentDTO toDTO(Department dept) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setDeptId(dept.getId());
        dto.setDeptName(dept.getDeptName());
        dto.setDeptCode(dept.getDeptCode());
//        dto.setHodId(dept.getHod() != null ? dept.getHod().getFacultyId() : null);
        return dto;
    }

    // Convert DTO → Entity
    private Department toEntity(DepartmentDTO dto) {
        Department dept = new Department();
        dept.setId(dto.getDeptId());
        dept.setDeptName(dto.getDeptName());
        dept.setDeptCode(dto.getDeptCode());

//        if (dto.getHodId() != null) {
//            Faculty hod = facultyRepository.findById(dto.getHodId())
//                    .orElseThrow(() -> new RuntimeException("Faculty (HOD) not found"));
//            dept.setHod(hod);
//        }
        return dept;
    }

    public DepartmentDTO createDepartment(DepartmentDTO dto) {
        Department dept = toEntity(dto);
        return toDTO(departmentRepository.save(dept));
    }

    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public DepartmentDTO getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    public DepartmentDTO updateDepartment(Long id, DepartmentDTO dto) {
        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        dept.setDeptName(dto.getDeptName());
        dept.setDeptCode(dto.getDeptCode());

//        if (dto.getHodId() != null) {
//            Faculty hod = facultyRepository.findById(dto.getHodId())
//                    .orElseThrow(() -> new RuntimeException("Faculty (HOD) not found"));
//            dept.setHod(hod);
//        }

        return toDTO(departmentRepository.save(dept));
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}
