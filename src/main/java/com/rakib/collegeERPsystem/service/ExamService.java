package com.rakib.collegeERPsystem.service;

import com.rakib.collegeERPsystem.dto.exam.ExamDTO;
import com.rakib.collegeERPsystem.dto.exam.ExamRequestDTO;
import com.rakib.collegeERPsystem.entity.Course;
import com.rakib.collegeERPsystem.entity.exam.Exam;
import com.rakib.collegeERPsystem.entity.exam.Question;
import com.rakib.collegeERPsystem.repository.CourseRepository;
import com.rakib.collegeERPsystem.repository.exam.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;
    private final CourseRepository courseRepository;

    // Convert Exam entity to DTO
    private ExamDTO convertToDTO(Exam exam) {
        return ExamDTO.builder()
                .examId(exam.getExamId())
                .examTitle(exam.getExamTitle())
                .academicYear(exam.getAcademicYear())
                .semester(exam.getSemester())
                .startTime(exam.getStartTime())
                .endTime(exam.getEndTime())
                .totalMarks(exam.getTotalMarks())
                .isPublished(exam.getIsPublished())
                .courseId(exam.getCourse() != null ? exam.getCourse().getId() : null)
                .courseName(exam.getCourse() != null ? exam.getCourse().getCourseName() : null)
                .questions(null) // populate if you want QuestionDTO mapping later
                .build();
    }

    // Create or update Exam
//    public ExamDTO saveExam(Exam exam) {
//        Exam savedExam = examRepository.save(exam);
//        return convertToDTO(savedExam);
//    }

    public Exam createExam(ExamRequestDTO dto) {
        Exam exam = Exam.builder()
                .examTitle(dto.getExamTitle())
                .academicYear(dto.getAcademicYear())
                .semester(dto.getSemester())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .totalMarks(dto.getTotalMarks())
                .isPublished(dto.getIsPublished())
                .build();

        // Set course reference
        courseRepository.findById(dto.getCourseId()).ifPresent(exam::setCourse);

        // Map questions if present
        if(dto.getQuestions() != null) {
            List<Question> questionList = dto.getQuestions().stream().map(qdto -> {
                Question q = new Question();
                q.setQuestionText(qdto.getQuestionText());
                q.setMarks(qdto.getMarks());
                q.setExam(exam);
                return q;
            }).toList();
            exam.setQuestions(questionList);
        }

        return examRepository.save(exam);
    }

    // Get Exam by ID
    public Optional<ExamDTO> getExamById(Long examId) {
        return examRepository.findById(examId).map(this::convertToDTO);
    }

    // Get all exams
    public List<ExamDTO> getAllExams() {
        return examRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Delete exam by ID
    public void deleteExam(Long examId) {
        examRepository.deleteById(examId);
    }

    // Get exams by course
    public List<ExamDTO> getExamsByCourseId(Long courseId) {
        return examRepository.findByCourse_Id(courseId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get exams by academic year and semester
    public List<ExamDTO> getExamsByYearAndSemester(String academicYear, String semester) {
        return examRepository.findByAcademicYearAndSemester(academicYear, semester)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}