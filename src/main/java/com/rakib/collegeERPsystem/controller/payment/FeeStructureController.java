package com.rakib.collegeERPsystem.controller.payment;

import com.rakib.collegeERPsystem.dto.payment.FeeStructureDTO;
import com.rakib.collegeERPsystem.service.payment.FeeStructureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fee-structures")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // ✅ Important if Angular is on localhost:4200
public class FeeStructureController {

    private final FeeStructureService service;

    @PostMapping
    public ResponseEntity<FeeStructureDTO> createFeeStructure(@RequestBody FeeStructureDTO dto) {
        return ResponseEntity.ok(service.createFeeStructure(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeeStructureDTO> updateFeeStructure(
            @PathVariable Long id,
            @RequestBody FeeStructureDTO dto) {
        return ResponseEntity.ok(service.updateFeeStructure(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeeStructureDTO> getFeeStructureById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getFeeStructureById(id));
    }

    @GetMapping
    public ResponseEntity<List<FeeStructureDTO>> getAllFeeStructures() {
        return ResponseEntity.ok(service.getAllFeeStructures());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeeStructure(@PathVariable Long id) {
        service.deleteFeeStructure(id);
        return ResponseEntity.noContent().build();
    }
}
