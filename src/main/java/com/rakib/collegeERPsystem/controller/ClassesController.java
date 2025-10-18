package com.rakib.collegeERPsystem.controller;

import com.rakib.collegeERPsystem.dtos.ClassesDTO;
import com.rakib.collegeERPsystem.dtos.ClassesCreateDTO;
import com.rakib.collegeERPsystem.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class ClassesController {

    @Autowired
    private ClassesService classesService;

    // ---------------- Create Class ----------------
    @PostMapping
    public ResponseEntity<ClassesDTO> createClass(@RequestBody ClassesCreateDTO createDTO) {
        ClassesDTO createdClass = classesService.createClass(createDTO);
        return ResponseEntity.ok(createdClass);
    }

    // ---------------- Update Class ----------------
    @PutMapping("/{id}")
    public ResponseEntity<ClassesDTO> updateClass(
            @PathVariable Long id,
            @RequestBody ClassesCreateDTO updateDTO
    ) {
        ClassesDTO updatedClass = classesService.updateClass(id, updateDTO);
        return ResponseEntity.ok(updatedClass);
    }

    // ---------------- Get Class by ID ----------------
    @GetMapping("/{id}")
    public ResponseEntity<ClassesDTO> getClassById(@PathVariable Long id) {
        ClassesDTO dto = classesService.getClassById(id);
        return ResponseEntity.ok(dto);
    }

    // ---------------- Get All Classes ----------------
    @GetMapping
    public ResponseEntity<List<ClassesDTO>> getAllClasses() {
        List<ClassesDTO> classesList = classesService.getAllClasses();
        return ResponseEntity.ok(classesList);
    }

    // ---------------- Delete Class ----------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long id) {
        classesService.deleteClass(id);
        return ResponseEntity.noContent().build();
    }
}
