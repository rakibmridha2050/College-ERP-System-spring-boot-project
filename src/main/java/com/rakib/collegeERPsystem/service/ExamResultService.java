package com.rakib.collegeERPsystem.service;

import com.rakib.collegeERPsystem.dto.exam.ExamResultDTO;
import com.rakib.collegeERPsystem.entity.Student;
import com.rakib.collegeERPsystem.entity.exam.Exam;
import com.rakib.collegeERPsystem.entity.exam.ExamResult;
import com.rakib.collegeERPsystem.repository.StudentRepository;
import com.rakib.collegeERPsystem.repository.exam.ExamRepository;
import com.rakib.collegeERPsystem.repository.exam.ExamResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ExamResultService {

    private final ExamResultRepository examResultRepository;
    private final ExamRepository examRepository;
    private final StudentRepository studentRepository;

    // Create or Update ExamResult
    public ExamResultDTO saveExamResult(ExamResultDTO dto) {
        ExamResult examResult = ExamResult.builder()
                .resultId(dto.getResultId())
                .totalMarks(dto.getTotalMarks())
                .obtainedMarks(dto.getObtainedMarks())
                .grade(dto.getGrade())
                .isPublished(dto.getIsPublished())
                .exam(examRepository.findById(dto.getExamId())
                        .orElseThrow(() -> new RuntimeException("Exam not found")))
                .student(studentRepository.findById(dto.getStudentId())
                        .orElseThrow(() -> new RuntimeException("Student not found")))
                .build();

        ExamResult savedResult = examResultRepository.save(examResult);
        return convertToDTO(savedResult);
    }

    // Get all exam results
    public List<ExamResultDTO> getAllResults() {
        return examResultRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get exam result by ID
    public ExamResultDTO getResultById(Long resultId) {
        return examResultRepository.findById(resultId)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("ExamResult not found"));
    }

    // Delete exam result
    public void deleteResult(Long resultId) {
        if (!examResultRepository.existsById(resultId)) {
            throw new RuntimeException("ExamResult not found");
        }
        examResultRepository.deleteById(resultId);
    }

    // Get results by exam
    public List<ExamResultDTO> getResultsByExamId(Long examId) {
        return examResultRepository.findByExam_ExamId(examId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get results by student
    public List<ExamResultDTO> getResultsByStudentId(Long studentId) {
        return examResultRepository.findByStudent_Id(studentId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get result by exam and student
    public ExamResultDTO getResultByExamAndStudent(Long examId, Long studentId) {
        return examResultRepository.findByExam_ExamIdAndStudent_Id(examId, studentId)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("ExamResult not found"));
    }

    // Convert entity to DTO
    private ExamResultDTO convertToDTO(ExamResult examResult) {
        return ExamResultDTO.builder()
                .resultId(examResult.getResultId())
                .examId(examResult.getExam().getExamId())
                .examTitle(examResult.getExam().getExamTitle())
                .studentId(examResult.getStudent().getId())
                .studentName(examResult.getStudent().getName())
                .totalMarks(examResult.getTotalMarks())
                .obtainedMarks(examResult.getObtainedMarks())
                .grade(examResult.getGrade())
                .isPublished(examResult.getIsPublished())
                .build();
    }
}
