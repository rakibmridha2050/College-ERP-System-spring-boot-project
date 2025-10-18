package com.rakib.collegeERPsystem.repository;

import com.rakib.collegeERPsystem.entity.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassesRepository extends JpaRepository<Classes, Long> {
    // Optional: find classes by name
    Classes findByClassName(String className);
}
