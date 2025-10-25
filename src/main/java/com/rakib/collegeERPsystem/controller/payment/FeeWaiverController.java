package com.rakib.collegeERPsystem.controller.payment;

import com.rakib.collegeERPsystem.dto.payment.FeeWaiverDTO;
import com.rakib.collegeERPsystem.enums.WaiverStatus;
import com.rakib.collegeERPsystem.service.payment.FeeWaiverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/fee-waivers")
@RequiredArgsConstructor
public class FeeWaiverController {

    private final FeeWaiverService feeWaiverService;

    @PostMapping
    public ResponseEntity<FeeWaiverDTO> createFeeWaiver(@Valid @RequestBody FeeWaiverDTO feeWaiverDTO) {
        FeeWaiverDTO createdWaiver = feeWaiverService.createFeeWaiver(feeWaiverDTO);
        return ResponseEntity.ok(createdWaiver);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeeWaiverDTO> updateFeeWaiver(
            @PathVariable Long id,
            @Valid @RequestBody FeeWaiverDTO feeWaiverDTO) {
        FeeWaiverDTO updatedWaiver = feeWaiverService.updateFeeWaiver(id, feeWaiverDTO);
        return ResponseEntity.ok(updatedWaiver);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeeWaiverDTO> getFeeWaiverById(@PathVariable Long id) {
        FeeWaiverDTO feeWaiver = feeWaiverService.getFeeWaiverById(id);
        return ResponseEntity.ok(feeWaiver);
    }

    @GetMapping
    public ResponseEntity<List<FeeWaiverDTO>> getAllFeeWaivers() {
        List<FeeWaiverDTO> feeWaivers = feeWaiverService.getAllFeeWaivers();
        return ResponseEntity.ok(feeWaivers);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<FeeWaiverDTO>> getFeeWaiversByStudentId(@PathVariable Long studentId) {
        List<FeeWaiverDTO> feeWaivers = feeWaiverService.getFeeWaiversByStudentId(studentId);
        return ResponseEntity.ok(feeWaivers);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<FeeWaiverDTO>> getFeeWaiversByStatus(@PathVariable WaiverStatus status) {
        List<FeeWaiverDTO> feeWaivers = feeWaiverService.getFeeWaiversByStatus(status);
        return ResponseEntity.ok(feeWaivers);
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<FeeWaiverDTO> approveFeeWaiver(
            @PathVariable Long id,
            @RequestParam String approvedBy) {
        FeeWaiverDTO approvedWaiver = feeWaiverService.approveFeeWaiver(id, approvedBy);
        return ResponseEntity.ok(approvedWaiver);
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<FeeWaiverDTO> rejectFeeWaiver(
            @PathVariable Long id,
            @RequestParam String rejectedBy) {
        FeeWaiverDTO rejectedWaiver = feeWaiverService.rejectFeeWaiver(id, rejectedBy);
        return ResponseEntity.ok(rejectedWaiver);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeeWaiver(@PathVariable Long id) {
        feeWaiverService.deleteFeeWaiver(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/student/{studentId}/total-approved-amount")
    public ResponseEntity<Double> getTotalApprovedWaiverAmount(@PathVariable Long studentId) {
        Double totalAmount = feeWaiverService.getTotalApprovedWaiverAmount(studentId);
        return ResponseEntity.ok(totalAmount);
    }
}