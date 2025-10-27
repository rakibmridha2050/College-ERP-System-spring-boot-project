package com.rakib.collegeERPsystem.controller;

import com.rakib.collegeERPsystem.dto.ClassesDTO;
import com.rakib.collegeERPsystem.dto.ClassesCreateDTO;
import com.rakib.collegeERPsystem.entity.Classes;
import com.rakib.collegeERPsystem.service.ClassesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
@RequiredArgsConstructor
public class ClassesController {


    private final ClassesService classesService;



    @PostMapping
    public ResponseEntity<ClassesDTO> createClass(@Valid @RequestBody ClassesCreateDTO createDTO) {
        ClassesDTO createdClass = classesService.createClass(createDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClass);
    }

    @GetMapping("/department/{departmentId}")
    public List<Classes> getClassesByDepartment(@PathVariable Long departmentId) {
        return classesService.getClassesByDepartment(departmentId);
    }



    @GetMapping
    public ResponseEntity<List<ClassesDTO>> getAllClasses() {
        List<ClassesDTO> classes = classesService.getAllClasses();
        return ResponseEntity.ok(classes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassesDTO> getClassById(@PathVariable Long id) {
        ClassesDTO classDTO = classesService.getClassById(id);
        return ResponseEntity.ok(classDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassesDTO> updateClass(@PathVariable Long id,
                                                  @Valid @RequestBody ClassesCreateDTO updateDTO) {
        ClassesDTO updatedClass = classesService.updateClass(id, updateDTO);
        return ResponseEntity.ok(updatedClass);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long id) {
        classesService.deleteClass(id);
        return ResponseEntity.noContent().build();
    }

}
