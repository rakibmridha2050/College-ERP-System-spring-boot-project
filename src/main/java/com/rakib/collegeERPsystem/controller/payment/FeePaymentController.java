package com.rakib.collegeERPsystem.controller.payment;

import com.rakib.collegeERPsystem.dto.payment.FeePaymentDTO;
import com.rakib.collegeERPsystem.enums.PaymentStatus;
import com.rakib.collegeERPsystem.service.payment.FeePaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/fee-payments")
@RequiredArgsConstructor
public class FeePaymentController {

    private final FeePaymentService feePaymentService;

    @PostMapping
    public ResponseEntity<FeePaymentDTO> createPayment(@Valid @RequestBody FeePaymentDTO paymentDTO) {
        FeePaymentDTO createdPayment = feePaymentService.createPayment(paymentDTO);
        return ResponseEntity.ok(createdPayment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeePaymentDTO> getPaymentById(@PathVariable Long id) {
        FeePaymentDTO payment = feePaymentService.getPaymentById(id);
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/student-fee/{studentFeeId}")
    public ResponseEntity<List<FeePaymentDTO>> getPaymentsByStudentFee(@PathVariable Long studentFeeId) {
        List<FeePaymentDTO> payments = feePaymentService.getPaymentsByStudentFee(studentFeeId);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<FeePaymentDTO>> getPaymentsByStudent(@PathVariable Long studentId) {
        List<FeePaymentDTO> payments = feePaymentService.getPaymentsByStudent(studentId);
        return ResponseEntity.ok(payments);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<FeePaymentDTO> updatePaymentStatus(
            @PathVariable Long id,
            @RequestParam PaymentStatus status) {
        FeePaymentDTO updatedPayment = feePaymentService.updatePaymentStatus(id, status);
        return ResponseEntity.ok(updatedPayment);
    }
}
