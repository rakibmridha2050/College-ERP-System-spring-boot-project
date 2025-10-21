package com.rakib.collegeERPsystem.dto.payment;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeeSummaryDTO {
    private Long studentId;
    private String studentName;
    private Double totalPayable;
    private Double totalPaid;
    private Double totalDue;
    private Double totalWaived;
}

