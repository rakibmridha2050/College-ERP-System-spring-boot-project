package com.rakib.collegeERPsystem.repository.payment;

import com.rakib.collegeERPsystem.entity.payment.FeePayment;
import com.rakib.collegeERPsystem.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FeePaymentRepository extends JpaRepository<FeePayment, Long> {
    List<FeePayment> findByStudentFeeId(Long studentFeeId);
    List<FeePayment> findByStatus(PaymentStatus status);
    List<FeePayment> findByPaymentDateBetween(LocalDate startDate, LocalDate endDate);
    Optional<FeePayment> findByTransactionId(String transactionId);

    @Query("SELECT SUM(fp.amountPaid) FROM FeePayment fp WHERE fp.studentFee.id = :studentFeeId AND fp.status = 'COMPLETED'")
    Double getTotalPaidAmountByStudentFeeId(@Param("studentFeeId") Long studentFeeId);

    @Query("SELECT COUNT(fp) FROM FeePayment fp WHERE fp.studentFee.student.id = :studentId AND fp.status = 'COMPLETED'")
    Long countSuccessfulPaymentsByStudentId(@Param("studentId") Long studentId);
}