package com.rakib.collegeERPsystem.service;


import com.rakib.collegeERPsystem.dto.StudentDTO;
import com.rakib.collegeERPsystem.entity.Student;
import com.rakib.collegeERPsystem.repository.DepartmentRepository;
import com.rakib.collegeERPsystem.repository.StudentRepository;
import com.rakib.collegeERPsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;

    // Create or Save
    public Student saveStudent(StudentDTO dto) {
//        Department department = departmentRepository.findById(dto.getDeptId())
//                .orElseThrow(() -> new RuntimeException("Department not found with id: " + dto.getDeptId()));

//        User user = null;
//        if (dto.getUserId() != null) {
//            user = userRepository.findById(dto.getUserId())
//                    .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getUserId()));
//        }

        Student student = new Student();
        student.setStudentId(dto.getStudentId());
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setPhone(dto.getPhone());
        student.setDob(dto.getDob());
        student.setGender(dto.getGender());
//        student.setAdmissionYear(dto.getAdmissionYear());
//        student.setDepartment(department);
//        student.setUser(user);

        return studentRepository.save(student);
    }

    // Read all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Read one student by ID
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    // Update
    public Student updateStudent(Long id, StudentDTO dto) {
        Student existingStudent = getStudentById(id);

//        Department department = departmentRepository.findById(dto.getDeptId())
//                .orElseThrow(() -> new RuntimeException("Department not found with id: " + dto.getDeptId()));
//
//        User user = null;
//        if (dto.getUserId() != null) {
//            user = userRepository.findById(dto.getUserId())
//                    .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getUserId()));
//        }

        existingStudent.setStudentId(dto.getStudentId());
        existingStudent.setName(dto.getName());
        existingStudent.setEmail(dto.getEmail());
        existingStudent.setPhone(dto.getPhone());
        existingStudent.setDob(dto.getDob());
        existingStudent.setGender(dto.getGender());
//        existingStudent.setAdmissionYear(dto.getAdmissionYear());
//        existingStudent.setDepartment(department);
//        existingStudent.setUser(user);

        return studentRepository.save(existingStudent);
    }

    // Delete
    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        studentRepository.delete(student);
    }


}
