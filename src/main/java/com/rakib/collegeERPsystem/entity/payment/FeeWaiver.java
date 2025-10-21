package com.rakib.collegeERPsystem.entity.payment;



import com.rakib.collegeERPsystem.entity.BaseEntity;
import com.rakib.collegeERPsystem.entity.Student;
import com.rakib.collegeERPsystem.enums.WaiverStatus;
import com.rakib.collegeERPsystem.enums.WaiverType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "fee_waivers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeeWaiver extends BaseEntity {



    @Column(name = "waiver_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private WaiverType waiverType; // MERIT, NEED_BASED, SPECIAL_QUOTA

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "waiver_amount")
    @PositiveOrZero
    private Double waiverAmount;

    @Column(name = "waiver_percentage")
    @Min(0)
    @Max(100)
    private Double waiverPercentage;

    @Column(name = "applicable_semester", nullable = false)
    private int applicableSemester;

    @Column(name = "approved_by")
    private String approvedBy;

    @Column(name = "approved_date")
    private LocalDate approvedDate;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private WaiverStatus status = WaiverStatus.PENDING;

    // 🔗 Many-to-One with Student
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;
}

