package com.rakib.collegeERPsystem.service;

import com.rakib.collegeERPsystem.dto.ClassesDTO;
import com.rakib.collegeERPsystem.dto.ClassesCreateDTO;
import com.rakib.collegeERPsystem.entity.Classes;
import com.rakib.collegeERPsystem.entity.Department;
import com.rakib.collegeERPsystem.exception.ResourceNotFoundException;
import com.rakib.collegeERPsystem.mapper.ClassesMapper;
import com.rakib.collegeERPsystem.repository.ClassesRepository;
import com.rakib.collegeERPsystem.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassesService {

    private final ClassesRepository classesRepository;
    private final DepartmentRepository departmentRepository;
    private final ClassesMapper classesMapper;

    // Constructor injection
    public ClassesService(ClassesRepository classesRepository,
                          DepartmentRepository departmentRepository,
                          ClassesMapper classesMapper) {
        this.classesRepository = classesRepository;
        this.departmentRepository = departmentRepository;
        this.classesMapper = classesMapper;
    }

    @Transactional
    public ClassesDTO createClass(ClassesCreateDTO createDTO) {
        // Check if class name already exists


        Classes classes = new Classes();
        classes.setClassName(createDTO.getClassName());

//        Department department = departmentRepository.findById(createDTO.getDepartmentId())
//                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + createDTO.getDepartmentId()));
//        if (department != null){  Classes existingClass = classesRepository.findByClassName(createDTO.getClassName());
//            if (existingClass != null) {
//                throw new RuntimeException("Class name already exists"); // Or use a custom exception
//            }}
//        classes.setDepartment(department);


        Department department = departmentRepository.findById(createDTO.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Department not found with id: " + createDTO.getDepartmentId()
                ));

// Check if class name already exists in the same department
        Classes existingClass = classesRepository.findByClassNameAndDepartment(
                createDTO.getClassName(), department
        );
        if (existingClass != null) {
            throw new ResourceNotFoundException(
                    "Class name '" + createDTO.getClassName() +
                            "' already exists in department '" + department.getDeptName() + "'"
            );
        }

// Assign department and other fields
        classes.setDepartment(department);


        // Set createdBy and updatedBy (assuming we have a method to get current user)
        String currentUser = getCurrentUser();
        classes.setCreatedBy(currentUser);
        classes.setUpdatedBy(currentUser);
        classes.setActive(true);
        System.out.println(classes.getActive());

        Classes savedClass = classesRepository.save(classes);
        return classesMapper.toDTO(savedClass);
    }

    @Transactional
    public ClassesDTO updateClass(Long id, ClassesCreateDTO updateDTO) {
        Classes classes = classesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Class not found with id: " + id));

        // Check if class name is being changed and if it already exists
        if (!classes.getClassName().equals(updateDTO.getClassName())) {
            Classes existingClass = classesRepository.findByClassName(updateDTO.getClassName());
            if (existingClass != null) {
                throw new RuntimeException("Class name already exists");
            }
        }

        classes.setClassName(updateDTO.getClassName());

        Department department = departmentRepository.findById(updateDTO.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + updateDTO.getDepartmentId()));
        classes.setDepartment(department);

        // Set updatedBy
        classes.setUpdatedBy(getCurrentUser());

        Classes updatedClass = classesRepository.save(classes);
        return classesMapper.toDTO(updatedClass);
    }



    @Transactional(readOnly = true)
    public ClassesDTO getClassById(Long id) {
        Classes classes = classesRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Class not found with id: " + id));
        return classesMapper.toDTO(classes);
    }




    @Transactional(readOnly = true)
    public List<ClassesDTO> getAllClasses() {
        return classesRepository.findByActiveTrue().stream()
                .map(classesMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteClass(Long id) {
        Classes classes = classesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Class not found with id: " + id));
        // Soft delete
//        classes.setActive(false);
//        classes.setUpdatedBy(getCurrentUser());
//        classesRepository.save(classes);
        classesRepository.deleteById(id);
    }

    public List<Classes> getClassesByDepartment(Long departmentId) {
        return classesRepository.findByDepartment_Id(departmentId);
    }






    private String getCurrentUser() {
        // Example: get the current user from security context
        // return SecurityContextHolder.getContext().getAuthentication().getName();
        // For now, return a placeholder
        return "system";
    }
}