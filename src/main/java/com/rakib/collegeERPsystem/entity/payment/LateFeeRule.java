package com.rakib.collegeERPsystem.entity.payment;



import com.rakib.collegeERPsystem.entity.BaseEntity;
import com.rakib.collegeERPsystem.enums.CalculationType;
import com.rakib.collegeERPsystem.enums.FineType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "late_fee_rules")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LateFeeRule extends BaseEntity {



    @Column(nullable = false)
    @NotBlank(message = "Rule name is required")
    private String name;

    @Column(name = "grace_period_in_days", nullable = false)
    @Min(0)
    private int gracePeriodInDays;

    @Column(name = "fine_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private FineType fineType; // FLAT or PERCENTAGE

    @Column(name = "fine_value", nullable = false)
    @Positive
    private Double fineValue;

    @Column(name = "calculation_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CalculationType calculationType; // PER_DAY or ONE_TIME

    @Column(name = "is_active")
    private Boolean isActive = true;
}
