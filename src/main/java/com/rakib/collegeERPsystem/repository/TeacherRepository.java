package com.rakib.collegeERPsystem.repository;

import com.rakib.collegeERPsystem.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    boolean existsByEmail(String email);
}
