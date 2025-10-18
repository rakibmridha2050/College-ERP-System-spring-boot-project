package com.rakib.collegeERPsystem.service;

import java.util.List;

public interface StudentExamService {
    StudentExamDTO assignExamToStudent(StudentExamDTO studentExamDTO);
    List<StudentExamDTO> getExamsByStudentId(Long studentId);
    void deleteStudentExam(Long id);
}
