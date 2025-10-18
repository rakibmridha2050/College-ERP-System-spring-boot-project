package com.rakib.collegeERPsystem.entity.exam;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student_answers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    private String chosenOption; // A, B, C, D
    private Boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "registration_id")
    private StudentExamRegistration registration;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}
