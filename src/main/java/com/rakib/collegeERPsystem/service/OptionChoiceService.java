package com.rakib.collegeERPsystem.service;

import com.rakib.collegeERPsystem.dto.exam.OptionChoiceDTO;
import com.rakib.collegeERPsystem.entity.exam.OptionChoice;
import com.rakib.collegeERPsystem.entity.exam.Question;
import com.rakib.collegeERPsystem.repository.exam.OptionChoiceRepository;
import com.rakib.collegeERPsystem.repository.exam.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OptionChoiceService {

    private final OptionChoiceRepository optionChoiceRepository;
    private final QuestionRepository questionRepository;

    /**
     * Create a new option for a specific question
     */
    public OptionChoiceDTO createOption(Long questionId, OptionChoiceDTO optionChoiceDTO) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question not found with ID: " + questionId));

        OptionChoice option = OptionChoice.builder()
                .optionLabel(optionChoiceDTO.getOptionLabel())
                .optionText(optionChoiceDTO.getOptionText())
                .question(question)
                .build();

        OptionChoice saved = optionChoiceRepository.save(option);
        return convertToDTO(saved);
    }

    /**
     * Get all options for a specific question
     */
    @Transactional(readOnly = true)
    public List<OptionChoiceDTO> getOptionsByQuestion(Long questionId) {
        List<OptionChoice> options = optionChoiceRepository.findByQuestion_QuestionId(questionId);
        return options.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Update an option by ID
     */
    public OptionChoiceDTO updateOption(Long optionId, OptionChoiceDTO optionChoiceDTO) {
        OptionChoice existing = optionChoiceRepository.findById(optionId)
                .orElseThrow(() -> new EntityNotFoundException("Option not found with ID: " + optionId));

        existing.setOptionLabel(optionChoiceDTO.getOptionLabel());
        existing.setOptionText(optionChoiceDTO.getOptionText());

        OptionChoice updated = optionChoiceRepository.save(existing);
        return convertToDTO(updated);
    }

    /**
     * Delete an option by ID
     */
    public void deleteOption(Long optionId) {
        if (!optionChoiceRepository.existsById(optionId)) {
            throw new EntityNotFoundException("Option not found with ID: " + optionId);
        }
        optionChoiceRepository.deleteById(optionId);
    }

    /**
     * Convert Entity → DTO
     */
    private OptionChoiceDTO convertToDTO(OptionChoice option) {
        return OptionChoiceDTO.builder()
                .optionId(option.getOptionId())
                .optionLabel(option.getOptionLabel())
                .optionText(option.getOptionText())
                .build();
    }
}