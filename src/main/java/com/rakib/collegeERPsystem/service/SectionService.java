package com.rakib.collegeERPsystem.service;

import com.rakib.collegeERPsystem.dtos.ClassesDTO;
import com.rakib.collegeERPsystem.dtos.SectionDTO;
import com.rakib.collegeERPsystem.dtos.SectionCreateDTO;
import com.rakib.collegeERPsystem.entity.Classes;
import com.rakib.collegeERPsystem.entity.Section;
import com.rakib.collegeERPsystem.repository.ClassesRepository;
import com.rakib.collegeERPsystem.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private ClassesRepository classesRepository;

    // ---------------- Create Section ----------------
    public SectionDTO createSection(SectionCreateDTO createDTO) {
        Section section = new Section();
        section.setSectionName(createDTO.getSectionName());

        Classes classes = classesRepository.findById(createDTO.getClassId())
                .orElseThrow(() -> new RuntimeException("Class not found"));
        section.setClasses(classes);

        Section savedSection = sectionRepository.save(section);
        return mapToDTO(savedSection);
    }

    // ---------------- Update Section ----------------
    public SectionDTO updateSection(Long id, SectionCreateDTO updateDTO) {
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Section not found"));

        section.setSectionName(updateDTO.getSectionName());

        Classes classes = classesRepository.findById(updateDTO.getClassId())
                .orElseThrow(() -> new RuntimeException("Class not found"));
        section.setClasses(classes);

        Section updatedSection = sectionRepository.save(section);
        return mapToDTO(updatedSection);
    }

    // ---------------- Get Section by ID ----------------
    public SectionDTO getSectionById(Long id) {
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Section not found"));
        return mapToDTO(section);
    }

    // ---------------- Get All Sections ----------------
    public List<SectionDTO> getAllSections() {
        return sectionRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ---------------- Delete Section ----------------
    public void deleteSection(Long id) {
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Section not found"));
        sectionRepository.delete(section);
    }

    // ---------------- Mapping Entity to DTO ----------------
    private SectionDTO mapToDTO(Section section) {
        SectionDTO dto = new SectionDTO();
        dto.setId(section.getId());
        dto.setSectionName(section.getSectionName());
        // You can optionally add Classes info in DTO if needed
        return dto;
    }

    // ---------------- Optional: Find Sections by Class ID ----------------
    public List<SectionDTO> getSectionsByClassId(Long classId) {
        List<Section> sections = sectionRepository.findByClassesId(classId);
        return sections.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
}
