package com.rakib.collegeERPsystem.controller;

import com.rakib.collegeERPsystem.dto.SectionDTO;
import com.rakib.collegeERPsystem.dto.SectionCreateDTO;
import com.rakib.collegeERPsystem.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sections")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    // ---------------- Create Section ----------------
    @PostMapping
    public ResponseEntity<SectionDTO> createSection(@RequestBody SectionCreateDTO createDTO) {
        SectionDTO createdSection = sectionService.createSection(createDTO);
        return ResponseEntity.ok(createdSection);
    }

    // ---------------- Update Section ----------------
    @PutMapping("/{id}")
    public ResponseEntity<SectionDTO> updateSection(
            @PathVariable Long id,
            @RequestBody SectionCreateDTO updateDTO
    ) {
        SectionDTO updatedSection = sectionService.updateSection(id, updateDTO);
        return ResponseEntity.ok(updatedSection);
    }

    // ---------------- Get Section by ID ----------------
    @GetMapping("/{id}")
    public ResponseEntity<SectionDTO> getSectionById(@PathVariable Long id) {
        SectionDTO dto = sectionService.getSectionById(id);
        return ResponseEntity.ok(dto);
    }

    // ---------------- Get All Sections ----------------
    @GetMapping
    public ResponseEntity<List<SectionDTO>> getAllSections() {
        List<SectionDTO> sections = sectionService.getAllSections();
        return ResponseEntity.ok(sections);
    }

    // ---------------- Delete Section ----------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSection(@PathVariable Long id) {
        sectionService.deleteSection(id);
        return ResponseEntity.noContent().build();
    }

    // ---------------- Optional: Get Sections by Class ID ----------------
    @GetMapping("/by-class/{classId}")
    public ResponseEntity<List<SectionDTO>> getSectionsByClassId(@PathVariable Long classId) {
        List<SectionDTO> sections = sectionService.getSectionsByClassId(classId);
        return ResponseEntity.ok(sections);
    }
}
