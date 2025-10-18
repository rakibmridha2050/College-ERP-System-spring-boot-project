package com.rakib.collegeERPsystem.service;

import com.rakib.collegeERPsystem.dto.ClassesDTO;
import com.rakib.collegeERPsystem.dto.ClassesCreateDTO;
import com.rakib.collegeERPsystem.dto.DepartmentDTO;
import com.rakib.collegeERPsystem.dto.SectionDTO;
import com.rakib.collegeERPsystem.entity.Classes;
import com.rakib.collegeERPsystem.entity.Department;

import com.rakib.collegeERPsystem.repository.ClassesRepository;
import com.rakib.collegeERPsystem.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassesService {

    @Autowired
    private ClassesRepository classesRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    // ---------------- CRUD Operations ----------------

    public ClassesDTO createClass(ClassesCreateDTO createDTO) {
        Classes classes = new Classes();
        classes.setClassName(createDTO.getClassName());

        Department department = departmentRepository.findById(createDTO.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));
        classes.setDepartment(department);

        Classes savedClass = classesRepository.save(classes);
        return mapToDTO(savedClass);
    }

    public ClassesDTO updateClass(Long id, ClassesCreateDTO updateDTO) {
        Classes classes = classesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Class not found"));

        classes.setClassName(updateDTO.getClassName());

        Department department = departmentRepository.findById(updateDTO.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));
        classes.setDepartment(department);

        Classes updatedClass = classesRepository.save(classes);
        return mapToDTO(updatedClass);
    }

    public ClassesDTO getClassById(Long id) {
        Classes classes = classesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Class not found"));
        return mapToDTO(classes);
    }

    public List<ClassesDTO> getAllClasses() {
        return classesRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public void deleteClass(Long id) {
        Classes classes = classesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Class not found"));
        classesRepository.delete(classes);
    }

    // ---------------- Mapping Entity to DTO ----------------
    private ClassesDTO mapToDTO(Classes classes) {
        ClassesDTO dto = new ClassesDTO();
        dto.setId(classes.getId());
        dto.setClassName(classes.getClassName());

        if (classes.getDepartment() != null) {
            DepartmentDTO deptDTO = new DepartmentDTO();
            deptDTO.setDeptId(classes.getDepartment().getId());
            deptDTO.setDeptName(classes.getDepartment().getDeptName());
            deptDTO.setDeptCode(classes.getDepartment().getDeptCode());
            // If HOD exists in the future, set it here
            // deptDTO.setHodId(classes.getDepartment().getHod().getId());
            dto.setDepartment(deptDTO);
        }

        if (classes.getSections() != null) {
            List<SectionDTO> sections = classes.getSections().stream().map(section -> {
                SectionDTO secDTO = new SectionDTO();
                secDTO.setId(section.getId());
                secDTO.setSectionName(section.getSectionName());
                return secDTO;
            }).collect(Collectors.toList());
            dto.setSections(sections);
        }

        return dto;
    }

}
