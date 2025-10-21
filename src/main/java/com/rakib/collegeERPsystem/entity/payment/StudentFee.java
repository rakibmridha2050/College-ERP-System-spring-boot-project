package com.rakib.collegeERPsystem.entity.payment;



import com.rakib.collegeERPsystem.entity.BaseEntity;
import com.rakib.collegeERPsystem.entity.Student;
import com.rakib.collegeERPsystem.enums.FeeInvoiceStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "student_fees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentFee extends BaseEntity {



    @Column(name = "invoice_number", nullable = false, unique = true)
    @NotBlank
    private String invoiceNumber;

    @Column(name = "issue_date", nullable = false)
    private LocalDate issueDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "total_amount", nullable = false)
    @PositiveOrZero
    private Double totalAmount;

    @Column(name = "late_fine")
    private Double lateFine = 0.0;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private FeeInvoiceStatus status = FeeInvoiceStatus.GENERATED;

    @Column(name = "academic_session", nullable = false)
    private String academicSession;

    @Column(name = "semester", nullable = false)
    private int semester;

    // 🔗 Many-to-One with Student
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    // 🔗 Many-to-One with FeeStructure
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fee_structure_id")
    private FeeStructure feeStructure;

    // 🔗 One-to-Many with FeePayment
    @OneToMany(mappedBy = "studentFee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FeePayment> feePayments;
}

