package com.rakib.collegeERPsystem.dto.exam;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentExamRegistrationDTO {
    private Long registrationId;
    private Long studentId;
    private String studentName;
    private Long examId;
    private String examTitle;
    private LocalDateTime registrationDate;

    private List<StudentAnswerDTO> answers;
}
