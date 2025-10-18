package com.rakib.collegeERPsystem.repository;

import com.rakib.collegeERPsystem.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    // Optional: find all sections by class ID
    List<Section> findByClassesId(Long classId);
}
