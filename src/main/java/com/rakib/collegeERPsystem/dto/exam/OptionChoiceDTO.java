package com.rakib.collegeERPsystem.dto.exam;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OptionChoiceDTO {
    private Long optionId;
    private String optionLabel; // A, B, C, D
    private String optionText;
}
