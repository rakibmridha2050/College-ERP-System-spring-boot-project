package com.rakib.collegeERPsystem.dto.payment;


import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LateFeeRuleDTO {
    private Long id;
    private String ruleName;       // e.g. "After 15 days late"
    private Double fineAmount;     // fixed fine or percentage
    private String fineType;       // FIXED / PERCENTAGE
    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;
    private Boolean isActive;
}

