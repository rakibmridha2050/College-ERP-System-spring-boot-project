package com.rakib.collegeERPsystem.entity.payment;



import com.rakib.collegeERPsystem.entity.BaseEntity;
import com.rakib.collegeERPsystem.enums.PaymentMethod;
import com.rakib.collegeERPsystem.enums.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "fee_payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeePayment extends BaseEntity {



    @Column(name = "amount_paid", nullable = false)
    @Positive(message = "Amount must be greater than zero")
    private Double amountPaid;

    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;

    @Column(name = "transaction_id", unique = true)
    private String transactionId;

    @Column(name = "payment_method", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(name = "sender_mobile_no")
    private String senderMobileNo; // For bKash/Nagad

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "bank_branch")
    private String bankBranch;

    @Column(name = "draft_number")
    private String draftNumber;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus status = PaymentStatus.PENDING;

    @Column(name = "verified_by")
    private String verifiedBy; // Admin username or ID

    // 🔗 Many-to-One with StudentFee
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_fee_id")
    private StudentFee studentFee;
}

