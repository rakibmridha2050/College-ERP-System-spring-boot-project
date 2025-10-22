package com.rakib.collegeERPsystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalTime;

@Entity
@Table(name = "schedule_classes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type; // Example: "Class"

    @Column(name = "start_time", nullable = false)

    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)


    private LocalTime endTime;

    @Column(nullable = false)
    private String status; // Active or Inactive
}
