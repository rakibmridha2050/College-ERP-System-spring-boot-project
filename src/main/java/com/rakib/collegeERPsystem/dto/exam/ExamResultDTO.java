package com.rakib.collegeERPsystem.dto.exam;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamResultDTO {
    private Long resultId;
    private Long examId;
    private String examTitle;
    private Long studentId;
    private String studentName;

    private Double totalMarks;
    private Double obtainedMarks;
    private String grade;
    private Boolean isPublished;
}
