package com.rakib.collegeERPsystem.repository;
import com.rakib.collegeERPsystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Find student by roll number
    Optional<Student> findByRollNo(String rollNo);

    // Find students by department
    List<Student> findByDepartmentId(Long deptId);

    // Find students by admission year
    List<Student> findByAdmissionYear(String admissionYear);

    // Find students by active status
    List<Student> findByActiveTrue();

    // Example: find by user ID
    Optional<Student> findByUserId(Long userId);
}