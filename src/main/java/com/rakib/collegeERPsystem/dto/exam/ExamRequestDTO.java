package com.rakib.collegeERPsystem.dto.exam;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamRequestDTO {
    private String examTitle;
    private String academicYear;
    private String semester;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Double totalMarks;
    private Boolean isPublished;
    private Long courseId;

    private List<QuestionRequestDTO> questions;
}
