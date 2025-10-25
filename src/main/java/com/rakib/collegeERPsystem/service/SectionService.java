package com.rakib.collegeERPsystem.service;

import com.rakib.collegeERPsystem.dto.SectionDTO;
import com.rakib.collegeERPsystem.dto.SectionCreateDTO;
import com.rakib.collegeERPsystem.entity.Classes;
import com.rakib.collegeERPsystem.entity.Section;
import com.rakib.collegeERPsystem.exception.ResourceNotFoundException;
import com.rakib.collegeERPsystem.repository.ClassesRepository;
import com.rakib.collegeERPsystem.repository.SectionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SectionService {

    private final SectionRepository sectionRepository;
    private final ClassesRepository classesRepository;

    // Constructor injection
    public SectionService(SectionRepository sectionRepository,
                          ClassesRepository classesRepository) {
        this.sectionRepository = sectionRepository;
        this.classesRepository = classesRepository;
    }

    // ---------------- Create Section ----------------
    public SectionDTO createSection(SectionCreateDTO createDTO) {
        String sectionName = createDTO.getSectionName().trim();

        // Check if there's an inactive section with the same name in the same class
        Section inactiveSection = sectionRepository.findBySectionNameAndClassesIdAndActiveFalse(
                sectionName, createDTO.getClassId());

        if (inactiveSection != null) {
            // Reactivate the existing section
            return reactivateSection(inactiveSection, createDTO);
        }

        // Check if active section with same name exists in the same class
        Section existingActiveSection = sectionRepository.findBySectionNameAndClassesIdAndActiveTrue(
                sectionName, createDTO.getClassId());
        if (existingActiveSection != null) {
            throw new RuntimeException("Section name '" + sectionName + "' already exists in this class");
        }

        Section section = new Section();
        section.setSectionName(sectionName);

        Classes classes = classesRepository.findByIdAndActiveTrue(createDTO.getClassId())
                .orElseThrow(() -> new ResourceNotFoundException("Class not found with id: " + createDTO.getClassId()));
        section.setClasses(classes);

        // Set createdBy and updatedBy
        String currentUser = getCurrentUser();
        section.setCreatedBy(currentUser);
        section.setUpdatedBy(currentUser);

        Section savedSection = sectionRepository.save(section);
        return mapToDTO(savedSection);
    }

    // ---------------- Update Section ----------------
    public SectionDTO updateSection(Long id, SectionCreateDTO updateDTO) {
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Section not found with id: " + id));

        String newSectionName = updateDTO.getSectionName().trim();

        // Check if section name is being changed
        if (!section.getSectionName().equals(newSectionName)) {
            // Check if there's an inactive section with the new name in the same class
            Section inactiveSection = sectionRepository.findBySectionNameAndClassesIdAndActiveFalse(
                    newSectionName, updateDTO.getClassId());
            if (inactiveSection != null) {
                throw new RuntimeException("Section name '" + newSectionName + "' was previously used in this class and cannot be reassigned");
            }

            // Check if active section with new name exists in the same class
            Section existingActiveSection = sectionRepository.findBySectionNameAndClassesIdAndActiveTrue(
                    newSectionName, updateDTO.getClassId());
            if (existingActiveSection != null) {
                throw new RuntimeException("Section name '" + newSectionName + "' already exists in this class");
            }
        }

        section.setSectionName(newSectionName);

        Classes classes = classesRepository.findByIdAndActiveTrue(updateDTO.getClassId())
                .orElseThrow(() -> new ResourceNotFoundException("Class not found with id: " + updateDTO.getClassId()));
        section.setClasses(classes);

        // Set updatedBy
        section.setUpdatedBy(getCurrentUser());

        Section updatedSection = sectionRepository.save(section);
        return mapToDTO(updatedSection);
    }

    // ---------------- Get Section by ID ----------------
    @Transactional(readOnly = true)
    public SectionDTO getSectionById(Long id) {
        Section section = sectionRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Section not found with id: " + id));
        return mapToDTO(section);
    }

    // ---------------- Get All Sections ----------------
    @Transactional(readOnly = true)
    public List<SectionDTO> getAllSections() {
        return sectionRepository.findByActiveTrue().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ---------------- Delete Section (Soft Delete) ----------------
    public void deleteSection(Long id) {
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Section not found with id: " + id));

        // Check if section has students
        if (section.getStudents() != null && !section.getStudents().isEmpty()) {
            throw new RuntimeException("Cannot delete section. There are students assigned to this section.");
        }

        // Soft delete
        section.setActive(false);
        section.setUpdatedBy(getCurrentUser());
        sectionRepository.save(section);
    }

    // ---------------- Get Sections by Class ID ----------------
    @Transactional(readOnly = true)
    public List<SectionDTO> getSectionsByClassId(Long classId) {
        List<Section> sections = sectionRepository.findByClassesIdAndActiveTrue(classId);
        return sections.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ---------------- Get All Sections Including Inactive ----------------
    @Transactional(readOnly = true)
    public List<SectionDTO> getAllSectionsIncludingInactive() {
        return sectionRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ---------------- Restore Section ----------------
    public SectionDTO restoreSection(Long id) {
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Section not found with id: " + id));

        // Check if there's an active section with the same name in the same class
        Section existingActiveSection = sectionRepository.findBySectionNameAndClassesIdAndActiveTrue(
                section.getSectionName(), section.getClasses().getId());
        if (existingActiveSection != null) {
            throw new RuntimeException("Cannot restore section. An active section with name '" + section.getSectionName() + "' already exists in this class");
        }

        section.setActive(true);
        section.setUpdatedBy(getCurrentUser());
        Section restoredSection = sectionRepository.save(section);
        return mapToDTO(restoredSection);
    }

    // ---------------- Reactivate Inactive Section ----------------
    private SectionDTO reactivateSection(Section inactiveSection, SectionCreateDTO createDTO) {
        inactiveSection.setActive(true);
        inactiveSection.setUpdatedBy(getCurrentUser());

        // Update class if different
        if (!inactiveSection.getClasses().getId().equals(createDTO.getClassId())) {
            Classes classes = classesRepository.findByIdAndActiveTrue(createDTO.getClassId())
                    .orElseThrow(() -> new ResourceNotFoundException("Class not found with id: " + createDTO.getClassId()));
            inactiveSection.setClasses(classes);
        }

        Section reactivatedSection = sectionRepository.save(inactiveSection);
        return mapToDTO(reactivatedSection);
    }

    // ---------------- Mapping Entity to DTO ----------------
    // In your SectionService.java - fix the mapToDTO method
    private SectionDTO mapToDTO(Section section) {
        SectionDTO dto = new SectionDTO();
        dto.setId(section.getId());
        dto.setSectionName(section.getSectionName());

        if (section.getClasses() != null) {
            SectionDTO.ClassInfoDTO classInfoDTO = new SectionDTO.ClassInfoDTO();
            classInfoDTO.setId(section.getClasses().getId());
            classInfoDTO.setClassName(section.getClasses().getClassName());

            if (section.getClasses().getDepartment() != null) {
                classInfoDTO.setDepartmentName(section.getClasses().getDepartment().getDeptName());
            }

            dto.setClassInfo(classInfoDTO);
        }

        return dto;
    }

    private String getCurrentUser() {
        // Implementation to get current user from security context
        // return SecurityContextHolder.getContext().getAuthentication().getName();
        return "system";
    }
}