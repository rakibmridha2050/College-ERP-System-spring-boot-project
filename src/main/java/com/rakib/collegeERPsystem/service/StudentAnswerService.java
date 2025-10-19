package com.rakib.collegeERPsystem.service;

import com.rakib.collegeERPsystem.dto.exam.StudentAnswerDTO;
import com.rakib.collegeERPsystem.entity.exam.Question;
import com.rakib.collegeERPsystem.entity.exam.StudentAnswer;
import com.rakib.collegeERPsystem.entity.exam.StudentExamRegistration;
import com.rakib.collegeERPsystem.repository.exam.QuestionRepository;
import com.rakib.collegeERPsystem.repository.exam.StudentAnswerRepository;
import com.rakib.collegeERPsystem.repository.exam.StudentExamRegistrationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentAnswerService {

    private final StudentAnswerRepository studentAnswerRepository;
    private final StudentExamRegistrationRepository registrationRepository;
    private final QuestionRepository questionRepository;

    /**
     * Submit a new answer for a registration
     */
    public StudentAnswerDTO submitAnswer(Long registrationId, Long questionId, StudentAnswerDTO answerDTO) {

        StudentExamRegistration registration = registrationRepository.findById(registrationId)
                .orElseThrow(() -> new EntityNotFoundException("Registration not found with ID: " + registrationId));

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question not found with ID: " + questionId));

        StudentAnswer answer = StudentAnswer.builder()
                .registration(registration)
                .question(question)
                .chosenOption(answerDTO.getChosenOption())
                .isCorrect(answerDTO.getChosenOption() != null
                        && answerDTO.getChosenOption().equalsIgnoreCase(question.getCorrectAnswer()))
                .build();

        StudentAnswer saved = studentAnswerRepository.save(answer);
        return convertToDTO(saved);
    }

    /**
     * Get all answers for a registration
     */
    @Transactional(readOnly = true)
    public List<StudentAnswerDTO> getAnswersByRegistration(Long registrationId) {
        List<StudentAnswer> answers = studentAnswerRepository.findByRegistration_RegistrationId(registrationId);
        return answers.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get all answers for a specific question
     */
    @Transactional(readOnly = true)
    public List<StudentAnswerDTO> getAnswersByQuestion(Long questionId) {
        List<StudentAnswer> answers = studentAnswerRepository.findByQuestion_QuestionId(questionId);
        return answers.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Update an existing answer
     */
    public StudentAnswerDTO updateAnswer(Long answerId, StudentAnswerDTO answerDTO) {
        StudentAnswer existing = studentAnswerRepository.findById(answerId)
                .orElseThrow(() -> new EntityNotFoundException("Answer not found with ID: " + answerId));

        existing.setChosenOption(answerDTO.getChosenOption());
        existing.setIsCorrect(existing.getQuestion() != null
                && answerDTO.getChosenOption() != null
                && answerDTO.getChosenOption().equalsIgnoreCase(existing.getQuestion().getCorrectAnswer()));

        StudentAnswer updated = studentAnswerRepository.save(existing);
        return convertToDTO(updated);
    }

    /**
     * Delete an answer by ID
     */
    public void deleteAnswer(Long answerId) {
        if (!studentAnswerRepository.existsById(answerId)) {
            throw new EntityNotFoundException("Answer not found with ID: " + answerId);
        }
        studentAnswerRepository.deleteById(answerId);
    }

    /**
     * Convert entity → DTO
     */
    private StudentAnswerDTO convertToDTO(StudentAnswer answer) {
        return StudentAnswerDTO.builder()
                .answerId(answer.getAnswerId())
                .questionId(answer.getQuestion() != null ? answer.getQuestion().getQuestionId() : null)
                .questionText(answer.getQuestion() != null ? answer.getQuestion().getQuestionText() : null)
                .chosenOption(answer.getChosenOption())
                .isCorrect(answer.getIsCorrect())
                .build();
    }
}
