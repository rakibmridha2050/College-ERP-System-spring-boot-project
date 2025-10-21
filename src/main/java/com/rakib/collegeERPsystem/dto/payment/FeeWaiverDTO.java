package com.rakib.collegeERPsystem.dto.payment;


import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeeWaiverDTO {
    private Long id;
    private Long studentId;
    private String studentName;
    private String reason;
    private Double waiverPercentage;
    private Double waiverAmount;
    private LocalDate appliedDate;
    private String approvedBy;
}

