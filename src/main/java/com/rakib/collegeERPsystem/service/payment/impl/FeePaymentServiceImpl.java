package com.rakib.collegeERPsystem.service.payment.impl;


import com.rakib.collegeERPsystem.dto.payment.FeePaymentDTO;
import com.rakib.collegeERPsystem.entity.payment.FeePayment;
import com.rakib.collegeERPsystem.entity.payment.StudentFee;
import com.rakib.collegeERPsystem.enums.PaymentStatus;
import com.rakib.collegeERPsystem.repository.payment.FeePaymentRepository;
import com.rakib.collegeERPsystem.repository.payment.StudentFeeRepository;
import com.rakib.collegeERPsystem.service.payment.FeePaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeePaymentServiceImpl implements FeePaymentService {

    private final FeePaymentRepository feePaymentRepository;
    private final StudentFeeRepository studentFeeRepository;

    @Override
    @Transactional
    public FeePaymentDTO createPayment(FeePaymentDTO paymentDTO) {
        try {
            StudentFee studentFee = studentFeeRepository.findById(paymentDTO.getStudentFeeId())
                    .orElseThrow(() -> new RuntimeException("Student fee record not found"));

            // Check if transaction ID already exists
            if (paymentDTO.getTransactionId() != null &&
                    feePaymentRepository.findByTransactionId(paymentDTO.getTransactionId()).isPresent()) {
                throw new RuntimeException("Transaction ID already exists");
            }

            FeePayment payment = FeePayment.builder()
                    .amountPaid(paymentDTO.getAmountPaid())
                    .paymentDate(paymentDTO.getPaymentDate().toLocalDate())
                    .transactionId(paymentDTO.getTransactionId())
                    .paymentMethod(com.rakib.collegeERPsystem.enums.PaymentMethod.valueOf(paymentDTO.getPaymentMethod()))
                    .status(PaymentStatus.COMPLETED)
                    .studentFee(studentFee)
                    .build();

            FeePayment savedPayment = feePaymentRepository.save(payment);

            // Update student fee status
            updateStudentFeeStatus(studentFee.getId());

            log.info("Payment created successfully for student fee ID: {}", paymentDTO.getStudentFeeId());
            return convertToDTO(savedPayment);
        } catch (Exception e) {
            log.error("Error creating payment: {}", e.getMessage());
            throw new RuntimeException("Failed to create payment: " + e.getMessage());
        }
    }

    @Override
    public FeePaymentDTO getPaymentById(Long id) {
        FeePayment payment = feePaymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        return convertToDTO(payment);
    }

    @Override
    public List<FeePaymentDTO> getPaymentsByStudentFee(Long studentFeeId) {
        return feePaymentRepository.findByStudentFeeId(studentFeeId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FeePaymentDTO> getPaymentsByStudent(Long studentId) {
        // This would require a custom query or service method
        // For now, returning empty list - implement based on your needs
        return List.of();
    }

    @Override
    @Transactional
    public FeePaymentDTO updatePaymentStatus(Long id, PaymentStatus status) {
        FeePayment payment = feePaymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        payment.setStatus(status);
        FeePayment updatedPayment = feePaymentRepository.save(payment);

        // Update student fee status if payment status changed
        if (payment.getStudentFee() != null) {
            updateStudentFeeStatus(payment.getStudentFee().getId());
        }

        return convertToDTO(updatedPayment);
    }

    private void updateStudentFeeStatus(Long studentFeeId) {
        StudentFee studentFee = studentFeeRepository.findById(studentFeeId)
                .orElseThrow(() -> new RuntimeException("Student fee not found"));

        Double totalPaid = feePaymentRepository.getTotalPaidAmountByStudentFeeId(studentFeeId);
        Double totalAmount = studentFee.getTotalAmount();

        if (totalPaid >= totalAmount) {
            studentFee.setStatus(com.rakib.collegeERPsystem.enums.FeeInvoiceStatus.PAID);
        } else if (totalPaid > 0) {
            studentFee.setStatus(com.rakib.collegeERPsystem.enums.FeeInvoiceStatus.PARTIALLY_PAID);
        } else {
            studentFee.setStatus(com.rakib.collegeERPsystem.enums.FeeInvoiceStatus.GENERATED);
        }

        studentFeeRepository.save(studentFee);
    }

    private FeePaymentDTO convertToDTO(FeePayment payment) {
        return FeePaymentDTO.builder()
                .id(payment.getId())
                .studentFeeId(payment.getStudentFee() != null ? payment.getStudentFee().getId() : null)
                .amountPaid(payment.getAmountPaid())
                .paymentDate(payment.getCreatedAt() != null ? payment.getCreatedAt() : LocalDateTime.now())
                .paymentMethod(payment.getPaymentMethod().name())
                .transactionId(payment.getTransactionId())
                .remarks("Payment processed successfully")
                .build();
    }
}