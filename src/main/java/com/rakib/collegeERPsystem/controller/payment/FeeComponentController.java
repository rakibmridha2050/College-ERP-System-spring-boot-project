package com.rakib.collegeERPsystem.controller.payment;

import com.rakib.collegeERPsystem.dto.payment.FeeComponentDTO;
import com.rakib.collegeERPsystem.service.payment.FeeComponentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fee-components")
@RequiredArgsConstructor
public class FeeComponentController {

    private final FeeComponentService service;

    // ✅ Create component under a FeeStructure
    @PostMapping("/structure/{feeStructureId}")
    public ResponseEntity<FeeComponentDTO> createFeeComponent(
            @PathVariable Long feeStructureId,
            @RequestBody FeeComponentDTO dto) {
        return ResponseEntity.ok(service.createFeeComponent(feeStructureId, dto));
    }

    // ✅ Update existing component
    @PutMapping("/{id}")
    public ResponseEntity<FeeComponentDTO> updateFeeComponent(
            @PathVariable Long id,
            @RequestBody FeeComponentDTO dto) {
        return ResponseEntity.ok(service.updateFeeComponent(id, dto));
    }

    // ✅ Get single component
    @GetMapping("/{id}")
    public ResponseEntity<FeeComponentDTO> getFeeComponentById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getFeeComponentById(id));
    }

    // ✅ Get all components under one structure
    @GetMapping("/structure/{feeStructureId}")
    public ResponseEntity<List<FeeComponentDTO>> getComponentsByStructure(@PathVariable Long feeStructureId) {
        return ResponseEntity.ok(service.getComponentsByFeeStructure(feeStructureId));
    }

    // ✅ Delete (soft delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeeComponent(@PathVariable Long id) {
        service.deleteFeeComponent(id);
        return ResponseEntity.noContent().build();
    }
}
