package com.rakib.collegeERPsystem.dto.payment;


import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeeStructureDTO {
    private Long id;
    private String structureName;     // e.g. "BSc CSE - Semester 1"
    private String academicYear;
    private String semester;
    private Boolean isActive;

    private List<FeeComponentDTO> feeComponents; // Each structure has multiple components
}
