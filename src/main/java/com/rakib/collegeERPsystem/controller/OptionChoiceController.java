package com.rakib.collegeERPsystem.controller;

import com.rakib.collegeERPsystem.dto.exam.OptionChoiceDTO;
import com.rakib.collegeERPsystem.service.OptionChoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions/{questionId}/options")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OptionChoiceController {

    private final OptionChoiceService optionChoiceService;

    /**
     * Create a new option for a specific question
     */
    @PostMapping
    public ResponseEntity<OptionChoiceDTO> createOption(
            @PathVariable Long questionId,
            @RequestBody OptionChoiceDTO optionChoiceDTO) {

        OptionChoiceDTO created = optionChoiceService.createOption(questionId, optionChoiceDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    /**
     * Get all options for a question
     */
    @GetMapping
    public ResponseEntity<List<OptionChoiceDTO>> getOptionsByQuestion(@PathVariable Long questionId) {
        List<OptionChoiceDTO> options = optionChoiceService.getOptionsByQuestion(questionId);
        return ResponseEntity.ok(options);
    }

    /**
     * Update an option by ID
     */
    @PutMapping("/{optionId}")
    public ResponseEntity<OptionChoiceDTO> updateOption(
            @PathVariable Long questionId,
            @PathVariable Long optionId,
            @RequestBody OptionChoiceDTO optionChoiceDTO) {

        OptionChoiceDTO updated = optionChoiceService.updateOption(optionId, optionChoiceDTO);
        return ResponseEntity.ok(updated);
    }

    /**
     * Delete an option by ID
     */
    @DeleteMapping("/{optionId}")
    public ResponseEntity<Void> deleteOption(
            @PathVariable Long questionId,
            @PathVariable Long optionId) {

        optionChoiceService.deleteOption(optionId);
        return ResponseEntity.noContent().build();
    }
}