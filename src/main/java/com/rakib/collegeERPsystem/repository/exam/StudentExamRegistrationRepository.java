package com.rakib.collegeERPsystem.repository.exam;

import com.rakib.collegeERPsystem.entity.exam.StudentExamRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentExamRegistrationRepository extends JpaRepository<StudentExamRegistration, Long> {

    List<StudentExamRegistration> findByExam_ExamId(Long examId);

    List<StudentExamRegistration> findByStudent_StudentId(Long studentId);

    Optional<StudentExamRegistration> findByExam_ExamIdAndStudent_StudentId(Long examId, Long studentId);
}
