package com.rakib.collegeERPsystem.controller;

import com.rakib.collegeERPsystem.dto.exam.StudentAnswerDTO;
import com.rakib.collegeERPsystem.service.StudentAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StudentAnswerController {

    private final StudentAnswerService studentAnswerService;

    /**
     * Submit a new answer for a student registration and question
     */
    @PostMapping("/registrations/{registrationId}/questions/{questionId}/answers")
    public ResponseEntity<StudentAnswerDTO> submitAnswer(
            @PathVariable Long registrationId,
            @PathVariable Long questionId,
            @RequestBody StudentAnswerDTO answerDTO) {

        StudentAnswerDTO savedAnswer = studentAnswerService.submitAnswer(registrationId, questionId, answerDTO);
        return new ResponseEntity<>(savedAnswer, HttpStatus.CREATED);
    }

    /**
     * Get all answers for a specific registration
     */
    @GetMapping("/registrations/{registrationId}/answers")
    public ResponseEntity<List<StudentAnswerDTO>> getAnswersByRegistration(@PathVariable Long registrationId) {
        List<StudentAnswerDTO> answers = studentAnswerService.getAnswersByRegistration(registrationId);
        return ResponseEntity.ok(answers);
    }

    /**
     * Get all answers for a specific question
     */
    @GetMapping("/questions/{questionId}/answers")
    public ResponseEntity<List<StudentAnswerDTO>> getAnswersByQuestion(@PathVariable Long questionId) {
        List<StudentAnswerDTO> answers = studentAnswerService.getAnswersByQuestion(questionId);
        return ResponseEntity.ok(answers);
    }

    /**
     * Update an existing answer by ID
     */
    @PutMapping("/answers/{answerId}")
    public ResponseEntity<StudentAnswerDTO> updateAnswer(
            @PathVariable Long answerId,
            @RequestBody StudentAnswerDTO answerDTO) {

        StudentAnswerDTO updatedAnswer = studentAnswerService.updateAnswer(answerId, answerDTO);
        return ResponseEntity.ok(updatedAnswer);
    }

    /**
     * Delete an answer by ID
     */
    @DeleteMapping("/answers/{answerId}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long answerId) {
        studentAnswerService.deleteAnswer(answerId);
        return ResponseEntity.noContent().build();
    }
}
