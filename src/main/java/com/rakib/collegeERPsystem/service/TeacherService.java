package com.rakib.collegeERPsystem.service;


import com.rakib.collegeERPsystem.dtos.TeacherDTO;
import com.rakib.collegeERPsystem.entity.Teacher;
import com.rakib.collegeERPsystem.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherService {


    private final TeacherRepository teacherRepository;

    // ✅ Mapper: DTO -> Entity
    private Teacher mapToEntity(TeacherDTO dto) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(dto.getFirstName());
        teacher.setLastName(dto.getLastName());
        teacher.setEmail(dto.getEmail());
        teacher.setPhoneNumber(dto.getPhoneNumber());
        teacher.setGender(dto.getGender());
        teacher.setDob(dto.getDob());
        teacher.setDesignation(dto.getDesignation());
        teacher.setAddress(dto.getAddress());
        return teacher;
    }

    // ✅ Mapper: Entity -> DTO
    private TeacherDTO mapToDTO(Teacher entity) {
        TeacherDTO dto = new TeacherDTO();
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setGender(entity.getGender());
        dto.setDob(entity.getDob());
        dto.setDesignation(entity.getDesignation());
        dto.setAddress(entity.getAddress());
        return dto;
    }

    // ✅ Create Teacher
    public TeacherDTO createTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = mapToEntity(teacherDTO);
        Teacher saved = teacherRepository.save(teacher);
        return mapToDTO(saved);
    }

    // ✅ Get All Teachers
    public List<TeacherDTO> getAllTeachers() {
        return teacherRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ✅ Get Teacher By Id
    public TeacherDTO getTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));
        return mapToDTO(teacher);
    }

    // ✅ Update Teacher
    public Teacher updateTeacher(Long id, Teacher teacherDetails) {
        Teacher existingTeacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        // Email check: যদি email same teacher-এর হয় তাহলে ok, না হলে duplicate check
        if (!existingTeacher.getEmail().equals(teacherDetails.getEmail())
                && teacherRepository.existsByEmail(teacherDetails.getEmail())) {
            throw new RuntimeException("Email already exists!");
        }

        existingTeacher.setFirstName(teacherDetails.getFirstName());
        existingTeacher.setLastName(teacherDetails.getLastName());
        existingTeacher.setEmail(teacherDetails.getEmail());
        existingTeacher.setPhoneNumber(teacherDetails.getPhoneNumber());
        // ... baki fields update করুন

        return teacherRepository.save(existingTeacher); // এখানে Hibernate update করবে
    }


    // ✅ Delete Teacher
    public void deleteTeacher(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));
        teacherRepository.delete(teacher);
    }
}
