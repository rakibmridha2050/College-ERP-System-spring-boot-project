package com.rakib.collegeERPsystem.repository.payment;

import com.rakib.collegeERPsystem.entity.payment.StudentFee;
import com.rakib.collegeERPsystem.enums.FeeInvoiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentFeeRepository extends JpaRepository<StudentFee, Long> {
    List<StudentFee> findByStudentId(Long studentId);
    List<StudentFee> findByStatus(FeeInvoiceStatus status);
    List<StudentFee> findByDueDateBeforeAndStatusNot(LocalDate date, FeeInvoiceStatus status);
    Optional<StudentFee> findByInvoiceNumber(String invoiceNumber);

    @Query("SELECT sf FROM StudentFee sf WHERE sf.student.id = :studentId AND sf.semester = :semester AND sf.academicSession = :academicSession")
    Optional<StudentFee> findByStudentAndSemesterAndSession(
            @Param("studentId") Long studentId,
            @Param("semester") int semester,
            @Param("academicSession") String academicSession);

    @Query("SELECT sf FROM StudentFee sf WHERE sf.dueDate < :today AND sf.status = 'GENERATED'")
    List<StudentFee> findOverdueInvoices(@Param("today") LocalDate today);
}