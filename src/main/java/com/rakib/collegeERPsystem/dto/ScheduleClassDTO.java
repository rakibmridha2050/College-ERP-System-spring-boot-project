package com.rakib.collegeERPsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleClassDTO {
    private Long id;
    private String type;

    private LocalTime startTime;

    private LocalTime endTime;
    private String status;
}
