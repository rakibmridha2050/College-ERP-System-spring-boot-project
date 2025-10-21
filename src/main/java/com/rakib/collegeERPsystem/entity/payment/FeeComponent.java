package com.rakib.collegeERPsystem.entity.payment;



import com.rakib.collegeERPsystem.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "fee_components")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeeComponent extends BaseEntity {



    @Column(nullable = false)
    @NotBlank(message = "Component name is required")
    private String name; // Tuition Fee, Lab Fee, Exam Fee, etc.

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    @PositiveOrZero(message = "Amount must be non-negative")
    private Double amount;

    @Column(name = "is_active")
    private Boolean isActive = true;

    // 🔗 Many-to-One with FeeStructure
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fee_structure_id")
    private FeeStructure feeStructure;
}

