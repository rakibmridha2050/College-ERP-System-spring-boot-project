package com.rakib.collegeERPsystem.service.payment;

import com.rakib.collegeERPsystem.dto.payment.FeeComponentDTO;

import java.util.List;

public interface FeeComponentService {

    FeeComponentDTO createFeeComponent(Long feeStructureId, FeeComponentDTO dto);
    FeeComponentDTO updateFeeComponent(Long id, FeeComponentDTO dto);
    FeeComponentDTO getFeeComponentById(Long id);
    List<FeeComponentDTO> getComponentsByFeeStructure(Long feeStructureId);
    void deleteFeeComponent(Long id);
}
