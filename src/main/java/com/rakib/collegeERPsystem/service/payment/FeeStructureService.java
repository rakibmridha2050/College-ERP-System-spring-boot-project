package com.rakib.collegeERPsystem.service.payment;

import com.rakib.collegeERPsystem.dto.payment.FeeStructureDTO;
import java.util.List;

public interface FeeStructureService {
    FeeStructureDTO createFeeStructure(FeeStructureDTO dto);
    FeeStructureDTO updateFeeStructure(Long id, FeeStructureDTO dto);
    FeeStructureDTO getFeeStructureById(Long id);
    List<FeeStructureDTO> getAllFeeStructures();
    void deleteFeeStructure(Long id);
}
