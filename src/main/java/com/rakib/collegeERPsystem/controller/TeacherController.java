//package com.rakib.collegeERPsystem.controller;
//
//import com.rakib.collegeERPsystem.dto.TeacherDTO;
//import com.rakib.collegeERPsystem.entity.Teacher;
//import com.rakib.collegeERPsystem.service.TeacherService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/teachers")
//@RequiredArgsConstructor
//public class TeacherController {
//
//    private final TeacherService teacherService;
//
//    // ✅ Create Teacher
//    @PostMapping
//    public ResponseEntity<TeacherDTO> createTeacher(@RequestBody TeacherDTO teacherDTO) {
//        TeacherDTO savedTeacher = teacherService.createTeacher(teacherDTO);
//        return ResponseEntity.ok(savedTeacher);
//    }
//
//    // ✅ Get All Teachers
//    @GetMapping
//    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
//        return ResponseEntity.ok(teacherService.getAllTeachers());
//    }
//
//    // ✅ Get Teacher By Id
//    @GetMapping("/{id}")
//    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable Long id) {
//        return ResponseEntity.ok(teacherService.getTeacherById(id));
//    }
//
//    // ✅ Update Teacher
//    @PutMapping("/{id}")
////    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable Long id, @RequestBody TeacherDTO teacherDTO) {
////        return ResponseEntity.ok(teacherService.updateTeacher(id, teacherDTO));
////    }
//
//
//    public ResponseEntity<Teacher> updateTeacher(
//            @PathVariable Long id,
//            @RequestBody Teacher teacherDetails) {
//
//        Teacher updatedTeacher = teacherService.updateTeacher(id, teacherDetails);
//        return ResponseEntity.ok(updatedTeacher);
//    }
//
//
//    // ✅ Delete Teacher
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteTeacher(@PathVariable Long id) {
//        teacherService.deleteTeacher(id);
//        return ResponseEntity.ok("Teacher deleted successfully with id: " + id);
//    }
//}
