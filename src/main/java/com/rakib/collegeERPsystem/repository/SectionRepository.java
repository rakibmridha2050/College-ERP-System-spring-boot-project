package com.rakib.collegeERPsystem.repository;

import com.rakib.collegeERPsystem.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {

    // Find sections by class ID (only active)
    List<Section> findByClassesIdAndActiveTrue(Long classId);

    // Find section by ID (only active)
    Optional<Section> findByIdAndActiveTrue(Long id);

    // Find all active sections
    List<Section> findByActiveTrue();

    // Find by section name and class ID (active)
    Section findBySectionNameAndClassesIdAndActiveTrue(String sectionName, Long classId);

    // Find by section name and class ID (inactive)
    Section findBySectionNameAndClassesIdAndActiveFalse(String sectionName, Long classId);

    // Check if section exists with name in class (active)
    boolean existsBySectionNameAndClassesIdAndActiveTrue(String sectionName, Long classId);
}