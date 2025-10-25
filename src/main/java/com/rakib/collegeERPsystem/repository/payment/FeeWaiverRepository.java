package com.rakib.collegeERPsystem.repository.payment;

import com.rakib.collegeERPsystem.entity.payment.FeeWaiver;
import com.rakib.collegeERPsystem.enums.WaiverStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeeWaiverRepository extends JpaRepository<FeeWaiver, Long> {

    // Find all fee waivers by student ID
    List<FeeWaiver> findByStudentId(Long studentId);

    // Find fee waivers by status
    List<FeeWaiver> findByStatus(WaiverStatus status);

    // Find fee waivers by student ID and status
    List<FeeWaiver> findByStudentIdAndStatus(Long studentId, WaiverStatus status);

    // Find fee waivers by semester
    List<FeeWaiver> findByApplicableSemester(int applicableSemester);

    // Find fee waivers by waiver type
    List<FeeWaiver> findByWaiverType(String waiverType);

    // Check if a fee waiver exists for student in specific semester
    boolean existsByStudentIdAndApplicableSemester(Long studentId, int applicableSemester);

    // Find pending fee waivers for a student
    @Query("SELECT fw FROM FeeWaiver fw WHERE fw.student.id = :studentId AND fw.status = 'PENDING'")
    List<FeeWaiver> findPendingWaiversByStudentId(@Param("studentId") Long studentId);

    // Calculate total waiver amount for a student
    @Query("SELECT COALESCE(SUM(fw.waiverAmount), 0) FROM FeeWaiver fw WHERE fw.student.id = :studentId AND fw.status = 'APPROVED'")
    Double getTotalApprovedWaiverAmountByStudentId(@Param("studentId") Long studentId);
}