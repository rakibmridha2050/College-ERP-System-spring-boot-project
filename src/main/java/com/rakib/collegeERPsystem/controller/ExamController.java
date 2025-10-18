package com.rakib.collegeERPsystem.controller;



import com.rakib.collegeERPsystem.dto.exam.ExamDTO;
import com.rakib.collegeERPsystem.dto.exam.ExamRequestDTO;
import com.rakib.collegeERPsystem.entity.exam.Exam;

import com.rakib.collegeERPsystem.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    // Create or update an exam
//    @PostMapping
//    public ResponseEntity<ExamDTO> saveExam(@RequestBody Exam exam) {
//        ExamDTO savedExam = examService.saveExam(exam);
//        return ResponseEntity.ok(savedExam);
//    }

    @PostMapping
    public ResponseEntity<Exam> createExam(@RequestBody ExamRequestDTO examRequestDTO) {
        Exam savedExam = examService.createExam(examRequestDTO);
        return ResponseEntity.ok(savedExam);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Exam> getExam(@PathVariable Long id) {
//        return examService.getExamById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

    // Get all exams
    @GetMapping
    public ResponseEntity<List<ExamDTO>> getAllExams() {
        List<ExamDTO> exams = examService.getAllExams();
        return ResponseEntity.ok(exams);
    }

    // Get exam by ID
    @GetMapping("/{id}")
    public ResponseEntity<ExamDTO> getExamById(@PathVariable("id") Long id) {
        return examService.getExamById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete exam by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable("id") Long id) {
        examService.deleteExam(id);
        return ResponseEntity.noContent().build();
    }

    // Get exams by course ID
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<ExamDTO>> getExamsByCourse(@PathVariable("courseId") Long courseId) {
        List<ExamDTO> exams = examService.getExamsByCourseId(courseId);
        return ResponseEntity.ok(exams);
    }

    // Get exams by academic year and semester
    @GetMapping("/search")
    public ResponseEntity<List<ExamDTO>> getExamsByYearAndSemester(
            @RequestParam String academicYear,
            @RequestParam String semester) {
        List<ExamDTO> exams = examService.getExamsByYearAndSemester(academicYear, semester);
        return ResponseEntity.ok(exams);
    }
}

