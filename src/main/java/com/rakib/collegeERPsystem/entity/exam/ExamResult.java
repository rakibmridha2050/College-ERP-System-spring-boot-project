package com.rakib.collegeERPsystem.entity.exam;

import com.rakib.collegeERPsystem.entity.Student;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "exam_results")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultId;

    private Double totalMarks;
    private Double obtainedMarks;
    private String grade;
    private Boolean isPublished;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
