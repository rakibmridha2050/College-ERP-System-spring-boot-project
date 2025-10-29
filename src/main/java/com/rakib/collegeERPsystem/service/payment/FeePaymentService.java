package com.rakib.collegeERPsystem.service.payment;


import com.rakib.collegeERPsystem.dto.payment.FeePaymentDTO;
import com.rakib.collegeERPsystem.enums.PaymentStatus;

import java.util.List;

public interface FeePaymentService {
    FeePaymentDTO createPayment(FeePaymentDTO paymentDTO);
    FeePaymentDTO getPaymentById(Long id);
    List<FeePaymentDTO> getPaymentsByStudentFee(Long studentFeeId);
    List<FeePaymentDTO> getPaymentsByStudent(Long studentId);
    FeePaymentDTO updatePaymentStatus(Long id, PaymentStatus status);
}

