package com.rakib.collegeERPsystem.dto.exam;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionRequestDTO {
    private String questionText;
    private Double marks;
}
