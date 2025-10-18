package com.rakib.collegeERPsystem.entity.exam;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "option_choices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OptionChoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionId;

    private String optionLabel; // A, B, C, D
    private String optionText;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}
