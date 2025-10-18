package com.rakib.collegeERPsystem.dto.exam;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentAnswerDTO {
    private Long answerId;
    private Long questionId;
    private String questionText;
    private String chosenOption; // A, B, C, D
    private Boolean isCorrect;
}
