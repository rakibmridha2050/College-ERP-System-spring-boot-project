package com.rakib.collegeERPsystem.controller;

import com.rakib.collegeERPsystem.dto.exam.ExamResultDTO;
import com.rakib.collegeERPsystem.service.ExamResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exam-results")
@RequiredArgsConstructor
public class ExamResultController {

    private final ExamResultService examResultService;

    // Create or update exam result
    @PostMapping
    public ResponseEntity<ExamResultDTO> saveExamResult(@RequestBody ExamResultDTO examResultDTO) {
        ExamResultDTO savedResult = examResultService.saveExamResult(examResultDTO);
        return ResponseEntity.ok(savedResult);
    }

    // Get all exam results
    @GetMapping
    public ResponseEntity<List<ExamResultDTO>> getAllResults() {
        List<ExamResultDTO> results = examResultService.getAllResults();
        return ResponseEntity.ok(results);
    }

    // Get exam result by ID
    @GetMapping("/{id}")
    public ResponseEntity<ExamResultDTO> getResultById(@PathVariable("id") Long resultId) {
        ExamResultDTO result = examResultService.getResultById(resultId);
        return ResponseEntity.ok(result);
    }

    // Delete exam result
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResult(@PathVariable("id") Long resultId) {
        examResultService.deleteResult(resultId);
        return ResponseEntity.noContent().build();
    }

    // Get results by exam ID
    @GetMapping("/exam/{examId}")
    public ResponseEntity<List<ExamResultDTO>> getResultsByExamId(@PathVariable Long examId) {
        List<ExamResultDTO> results = examResultService.getResultsByExamId(examId);
        return ResponseEntity.ok(results);
    }

    // Get results by student ID
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ExamResultDTO>> getResultsByStudentId(@PathVariable Long studentId) {
        List<ExamResultDTO> results = examResultService.getResultsByStudentId(studentId);
        return ResponseEntity.ok(results);
    }

    // Get result by exam ID and student ID
    @GetMapping("/exam/{examId}/student/{studentId}")
    public ResponseEntity<ExamResultDTO> getResultByExamAndStudent(@PathVariable Long examId,
                                                                   @PathVariable Long studentId) {
        ExamResultDTO result = examResultService.getResultByExamAndStudent(examId, studentId);
        return ResponseEntity.ok(result);
    }
}
