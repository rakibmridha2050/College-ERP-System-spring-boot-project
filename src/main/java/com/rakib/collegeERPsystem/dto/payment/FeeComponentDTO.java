package com.rakib.collegeERPsystem.dto.payment;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeeComponentDTO {
    private Long id;
    private String componentName;   // e.g. Tuition Fee, Library Fee
    private Double amount;
    private String description;
    private Boolean isActive;
}

