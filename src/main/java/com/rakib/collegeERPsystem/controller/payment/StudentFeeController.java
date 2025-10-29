package com.rakib.collegeERPsystem.controller.payment;


import com.rakib.collegeERPsystem.dto.payment.StudentFeeDTO;
import com.rakib.collegeERPsystem.service.payment.StudentFeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/student-fees")
@RequiredArgsConstructor
public class StudentFeeController {

    private final StudentFeeService studentFeeService;

    @PostMapping
    public ResponseEntity<StudentFeeDTO> generateFeeInvoice(@Valid @RequestBody StudentFeeDTO studentFeeDTO) {
        StudentFeeDTO generatedInvoice = studentFeeService.generateFeeInvoice(studentFeeDTO);
        return ResponseEntity.ok(generatedInvoice);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentFeeDTO> getStudentFeeById(@PathVariable Long id) {
        StudentFeeDTO studentFee = studentFeeService.getStudentFeeById(id);
        return ResponseEntity.ok(studentFee);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<StudentFeeDTO>> getStudentFeesByStudentId(@PathVariable Long studentId) {
        List<StudentFeeDTO> studentFees = studentFeeService.getStudentFeesByStudentId(studentId);
        return ResponseEntity.ok(studentFees);
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<StudentFeeDTO>> getOverdueInvoices() {
        List<StudentFeeDTO> overdueInvoices = studentFeeService.getOverdueInvoices();
        return ResponseEntity.ok(overdueInvoices);
    }

    @PostMapping("/{id}/apply-late-fee")
    public ResponseEntity<Void> applyLateFee(
            @PathVariable Long id,
            @RequestParam Double lateFeeAmount) {
        studentFeeService.applyLateFee(id, lateFeeAmount);
        return ResponseEntity.ok().build();
    }
}