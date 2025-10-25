package com.rakib.collegeERPsystem.service.payment;

import com.rakib.collegeERPsystem.dto.payment.LateFeeRuleDTO;
import com.rakib.collegeERPsystem.enums.CalculationType;
import com.rakib.collegeERPsystem.enums.FineType;

import java.util.List;

public interface LateFeeRuleService {

    LateFeeRuleDTO createLateFeeRule(LateFeeRuleDTO lateFeeRuleDTO);

    LateFeeRuleDTO updateLateFeeRule(Long id, LateFeeRuleDTO lateFeeRuleDTO);

    LateFeeRuleDTO getLateFeeRuleById(Long id);

    List<LateFeeRuleDTO> getAllLateFeeRules();

    List<LateFeeRuleDTO> getActiveLateFeeRules();

    List<LateFeeRuleDTO> getLateFeeRulesByFineType(FineType fineType);

    List<LateFeeRuleDTO> getLateFeeRulesByCalculationType(CalculationType calculationType);

    LateFeeRuleDTO toggleLateFeeRuleStatus(Long id);

    void deleteLateFeeRule(Long id);

    Double calculateLateFee(Double originalAmount, int daysLate);

    LateFeeRuleDTO getApplicableLateFeeRule(int daysLate);
}