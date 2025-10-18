package com.rakib.collegeERPsystem.entity.exam;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rakib.collegeERPsystem.entity.Course;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "exams")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examId;

    private String examTitle;
    private String academicYear;
    private String semester;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Double totalMarks;
    private Boolean isPublished;

    @ManyToOne
    @JoinColumn(name = "course_id")

    private Course course;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, orphanRemoval = true)

    private List<Question> questions;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentExamRegistration> registrations;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExamResult> results;
}
