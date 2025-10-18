package com.rakib.collegeERPsystem.dto.exam;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDTO {
    private Long questionId;
    private String questionText;
    private Double marks;
    private String correctAnswer; // Optional — only for teacher/admin

    private List<OptionChoiceDTO> options;
}
