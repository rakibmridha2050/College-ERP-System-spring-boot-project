package com.rakib.collegeERPsystem.dto.payment;


import com.rakib.collegeERPsystem.enums.FeeStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeeStructureDTO {
    private Long id;
    private String program;
    private Integer semester;
    private String studentCategory;
    private String academicSession;
    private Double totalAmount;
    private FeeStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
    private Boolean active;
    private List<FeeComponentDTO> feeComponents;
}
