package com.rakib.collegeERPsystem.service.payment;

import com.rakib.collegeERPsystem.dto.payment.FeeWaiverDTO;
import com.rakib.collegeERPsystem.enums.WaiverStatus;
import java.util.List;

public interface FeeWaiverService {

    FeeWaiverDTO createFeeWaiver(FeeWaiverDTO feeWaiverDTO);

    FeeWaiverDTO updateFeeWaiver(Long id, FeeWaiverDTO feeWaiverDTO);

    FeeWaiverDTO getFeeWaiverById(Long id);

    List<FeeWaiverDTO> getAllFeeWaivers();

    List<FeeWaiverDTO> getFeeWaiversByStudentId(Long studentId);

    List<FeeWaiverDTO> getFeeWaiversByStatus(WaiverStatus status);

    FeeWaiverDTO approveFeeWaiver(Long id, String approvedBy);

    FeeWaiverDTO rejectFeeWaiver(Long id, String rejectedBy);

    void deleteFeeWaiver(Long id);

    Double getTotalApprovedWaiverAmount(Long studentId);

    boolean hasPendingWaiverInSemester(Long studentId, int semester);
}