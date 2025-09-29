package com.rakib.collegeERPsystem.repository;
import com.rakib.collegeERPsystem.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    // Find all faculty by department ID
    List<Faculty> findByDepartmentId(Long departmentId);

    // Find all faculty by user ID
    List<Faculty> findByUserId(Long userId);

    // Optional: find faculty by email
    Faculty findByEmail(String email);
}
