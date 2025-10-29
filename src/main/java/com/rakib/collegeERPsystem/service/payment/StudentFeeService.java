package com.rakib.collegeERPsystem.service.payment;

import com.rakib.collegeERPsystem.dto.payment.StudentFeeDTO;

import java.util.List;

public interface StudentFeeService {
    StudentFeeDTO generateFeeInvoice(StudentFeeDTO studentFeeDTO);
    StudentFeeDTO getStudentFeeById(Long id);
    List<StudentFeeDTO> getStudentFeesByStudentId(Long studentId);
    List<StudentFeeDTO> getOverdueInvoices();
    void applyLateFee(Long studentFeeId, Double lateFeeAmount);
}