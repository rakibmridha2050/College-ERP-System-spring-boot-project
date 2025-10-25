package com.rakib.collegeERPsystem.controller.payment;

import com.rakib.collegeERPsystem.dto.payment.LateFeeRuleDTO;
import com.rakib.collegeERPsystem.enums.CalculationType;
import com.rakib.collegeERPsystem.enums.FineType;
import com.rakib.collegeERPsystem.service.payment.LateFeeRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/late-fee-rules")
@RequiredArgsConstructor
public class LateFeeRuleController {

    private final LateFeeRuleService lateFeeRuleService;

    @PostMapping
    public ResponseEntity<LateFeeRuleDTO> createLateFeeRule(@Valid @RequestBody LateFeeRuleDTO lateFeeRuleDTO) {
        LateFeeRuleDTO createdRule = lateFeeRuleService.createLateFeeRule(lateFeeRuleDTO);
        return ResponseEntity.ok(createdRule);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LateFeeRuleDTO> updateLateFeeRule(
            @PathVariable Long id,
            @Valid @RequestBody LateFeeRuleDTO lateFeeRuleDTO) {
        LateFeeRuleDTO updatedRule = lateFeeRuleService.updateLateFeeRule(id, lateFeeRuleDTO);
        return ResponseEntity.ok(updatedRule);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LateFeeRuleDTO> getLateFeeRuleById(@PathVariable Long id) {
        LateFeeRuleDTO lateFeeRule = lateFeeRuleService.getLateFeeRuleById(id);
        return ResponseEntity.ok(lateFeeRule);
    }

    @GetMapping
    public ResponseEntity<List<LateFeeRuleDTO>> getAllLateFeeRules() {
        List<LateFeeRuleDTO> lateFeeRules = lateFeeRuleService.getAllLateFeeRules();
        return ResponseEntity.ok(lateFeeRules);
    }

    @GetMapping("/active")
    public ResponseEntity<List<LateFeeRuleDTO>> getActiveLateFeeRules() {
        List<LateFeeRuleDTO> activeRules = lateFeeRuleService.getActiveLateFeeRules();
        return ResponseEntity.ok(activeRules);
    }

    @GetMapping("/fine-type/{fineType}")
    public ResponseEntity<List<LateFeeRuleDTO>> getLateFeeRulesByFineType(@PathVariable FineType fineType) {
        List<LateFeeRuleDTO> rules = lateFeeRuleService.getLateFeeRulesByFineType(fineType);
        return ResponseEntity.ok(rules);
    }

    @GetMapping("/calculation-type/{calculationType}")
    public ResponseEntity<List<LateFeeRuleDTO>> getLateFeeRulesByCalculationType(
            @PathVariable CalculationType calculationType) {
        List<LateFeeRuleDTO> rules = lateFeeRuleService.getLateFeeRulesByCalculationType(calculationType);
        return ResponseEntity.ok(rules);
    }

    @PatchMapping("/{id}/toggle-status")
    public ResponseEntity<LateFeeRuleDTO> toggleLateFeeRuleStatus(@PathVariable Long id) {
        LateFeeRuleDTO updatedRule = lateFeeRuleService.toggleLateFeeRuleStatus(id);
        return ResponseEntity.ok(updatedRule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLateFeeRule(@PathVariable Long id) {
        lateFeeRuleService.deleteLateFeeRule(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/calculate-late-fee")
    public ResponseEntity<Double> calculateLateFee(
            @RequestParam Double originalAmount,
            @RequestParam int daysLate) {
        Double lateFee = lateFeeRuleService.calculateLateFee(originalAmount, daysLate);
        return ResponseEntity.ok(lateFee);
    }

    @GetMapping("/applicable-rule")
    public ResponseEntity<LateFeeRuleDTO> getApplicableLateFeeRule(@RequestParam int daysLate) {
        LateFeeRuleDTO applicableRule = lateFeeRuleService.getApplicableLateFeeRule(daysLate);
        return ResponseEntity.ok(applicableRule);
    }
}