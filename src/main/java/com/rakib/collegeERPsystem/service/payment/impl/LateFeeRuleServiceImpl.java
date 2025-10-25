package com.rakib.collegeERPsystem.service.payment.impl;

import com.rakib.collegeERPsystem.dto.payment.LateFeeRuleDTO;
import com.rakib.collegeERPsystem.entity.payment.LateFeeRule;
import com.rakib.collegeERPsystem.enums.CalculationType;
import com.rakib.collegeERPsystem.enums.FineType;
import com.rakib.collegeERPsystem.repository.payment.LateFeeRuleRepository;
import com.rakib.collegeERPsystem.service.payment.LateFeeRuleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LateFeeRuleServiceImpl implements LateFeeRuleService {

    private final LateFeeRuleRepository lateFeeRuleRepository;

    @Override
    @Transactional
    public LateFeeRuleDTO createLateFeeRule(LateFeeRuleDTO lateFeeRuleDTO) {
        try {
            // Check if rule name already exists
            if (lateFeeRuleRepository.existsByName(lateFeeRuleDTO.getRuleName())) {
                throw new RuntimeException("Late fee rule with name '" + lateFeeRuleDTO.getRuleName() + "' already exists");
            }

            LateFeeRule lateFeeRule = LateFeeRule.builder()
                    .name(lateFeeRuleDTO.getRuleName())
                    .gracePeriodInDays(calculateGracePeriodFromName(lateFeeRuleDTO.getRuleName()))
                    .fineType(FineType.valueOf(lateFeeRuleDTO.getFineType()))
                    .fineValue(lateFeeRuleDTO.getFineAmount())
                    .calculationType(determineCalculationType(lateFeeRuleDTO))
                    .isActive(lateFeeRuleDTO.getIsActive() != null ? lateFeeRuleDTO.getIsActive() : true)
                    .build();

            LateFeeRule savedRule = lateFeeRuleRepository.save(lateFeeRule);
            log.info("Late fee rule created successfully: {}", lateFeeRuleDTO.getRuleName());

            return convertToDTO(savedRule);
        } catch (Exception e) {
            log.error("Error creating late fee rule: {}", e.getMessage());
            throw new RuntimeException("Failed to create late fee rule: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public LateFeeRuleDTO updateLateFeeRule(Long id, LateFeeRuleDTO lateFeeRuleDTO) {
        try {
            LateFeeRule existingRule = lateFeeRuleRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Late fee rule not found with id: " + id));

            // Check if rule name already exists (excluding current rule)
            if (lateFeeRuleRepository.existsByNameAndIdNot(lateFeeRuleDTO.getRuleName(), id)) {
                throw new RuntimeException("Late fee rule with name '" + lateFeeRuleDTO.getRuleName() + "' already exists");
            }

            // Update fields
            existingRule.setName(lateFeeRuleDTO.getRuleName());
            existingRule.setGracePeriodInDays(calculateGracePeriodFromName(lateFeeRuleDTO.getRuleName()));
            existingRule.setFineType(FineType.valueOf(lateFeeRuleDTO.getFineType()));
            existingRule.setFineValue(lateFeeRuleDTO.getFineAmount());
            existingRule.setCalculationType(determineCalculationType(lateFeeRuleDTO));

            if (lateFeeRuleDTO.getIsActive() != null) {
                existingRule.setIsActive(lateFeeRuleDTO.getIsActive());
            }

            LateFeeRule updatedRule = lateFeeRuleRepository.save(existingRule);
            log.info("Late fee rule updated successfully with ID: {}", id);

            return convertToDTO(updatedRule);
        } catch (Exception e) {
            log.error("Error updating late fee rule with ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to update late fee rule: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public LateFeeRuleDTO toggleLateFeeRuleStatus(Long id) {
        try {
            LateFeeRule lateFeeRule = lateFeeRuleRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Late fee rule not found with id: " + id));

            lateFeeRule.setIsActive(!lateFeeRule.getIsActive());

            LateFeeRule updatedRule = lateFeeRuleRepository.save(lateFeeRule);
            log.info("Late fee rule status toggled to {} for ID: {}", updatedRule.getIsActive(), id);

            return convertToDTO(updatedRule);
        } catch (Exception e) {
            log.error("Error toggling late fee rule status with ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to toggle late fee rule status: " + e.getMessage());
        }
    }

    @Override
    public LateFeeRuleDTO getLateFeeRuleById(Long id) {
        LateFeeRule lateFeeRule = lateFeeRuleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Late fee rule not found with id: " + id));
        return convertToDTO(lateFeeRule);
    }

    @Override
    public List<LateFeeRuleDTO> getAllLateFeeRules() {
        return lateFeeRuleRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LateFeeRuleDTO> getActiveLateFeeRules() {
        return lateFeeRuleRepository.findByIsActiveTrue()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LateFeeRuleDTO> getLateFeeRulesByFineType(FineType fineType) {
        return lateFeeRuleRepository.findByFineType(fineType)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LateFeeRuleDTO> getLateFeeRulesByCalculationType(CalculationType calculationType) {
        return lateFeeRuleRepository.findByCalculationType(calculationType)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteLateFeeRule(Long id) {
        try {
            LateFeeRule lateFeeRule = lateFeeRuleRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Late fee rule not found with id: " + id));

            lateFeeRuleRepository.delete(lateFeeRule);
            log.info("Late fee rule deleted successfully with ID: {}", id);
        } catch (Exception e) {
            log.error("Error deleting late fee rule with ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to delete late fee rule: " + e.getMessage());
        }
    }

    @Override
    public Double calculateLateFee(Double originalAmount, int daysLate) {
        try {
            if (daysLate <= 0) {
                return 0.0;
            }

            LateFeeRuleDTO applicableRule = getApplicableLateFeeRule(daysLate);
            if (applicableRule == null) {
                return 0.0;
            }

            Double fineAmount = applicableRule.getFineAmount();
            FineType fineType = FineType.valueOf(applicableRule.getFineType());

            if (fineType == FineType.FLAT) {
                return fineAmount;
            } else if (fineType == FineType.PERCENTAGE) {
                return (originalAmount * fineAmount) / 100;
            }

            return 0.0;
        } catch (Exception e) {
            log.error("Error calculating late fee: {}", e.getMessage());
            return 0.0;
        }
    }

    @Override
    public LateFeeRuleDTO getApplicableLateFeeRule(int daysLate) {
        try {
            // Get all active rules that apply to the given days late
            List<LateFeeRule> applicableRules = lateFeeRuleRepository
                    .findByGracePeriodInDaysLessThanEqualAndIsActiveTrue(daysLate);

            if (applicableRules.isEmpty()) {
                return null;
            }

            // Find the rule with the highest grace period that's still less than or equal to days late
            LateFeeRule applicableRule = applicableRules.stream()
                    .max(Comparator.comparingInt(LateFeeRule::getGracePeriodInDays))
                    .orElse(null);

            return applicableRule != null ? convertToDTO(applicableRule) : null;
        } catch (Exception e) {
            log.error("Error finding applicable late fee rule: {}", e.getMessage());
            return null;
        }
    }

    private LateFeeRuleDTO convertToDTO(LateFeeRule lateFeeRule) {
        return LateFeeRuleDTO.builder()
                .id(lateFeeRule.getId())
                .ruleName(lateFeeRule.getName())
                .fineAmount(lateFeeRule.getFineValue())
                .fineType(lateFeeRule.getFineType().name())
                .effectiveFrom(lateFeeRule.getCreatedAt() != null ? lateFeeRule.getCreatedAt().toLocalDate() : null)
                .isActive(lateFeeRule.getIsActive())
                .build();
    }

    private int calculateGracePeriodFromName(String ruleName) {
        try {
            // Extract numbers from rule name (e.g., "After 15 days late" -> 15)
            String numbers = ruleName.replaceAll("[^0-9]", "");
            return numbers.isEmpty() ? 0 : Integer.parseInt(numbers);
        } catch (Exception e) {
            log.warn("Could not extract grace period from rule name: {}, using default 0", ruleName);
            return 0;
        }
    }

    private CalculationType determineCalculationType(LateFeeRuleDTO dto) {
        // You can implement logic based on fine type and rule name
        // For now, defaulting to ONE_TIME, but you can make this smarter
        String ruleName = dto.getRuleName().toLowerCase();
        if (ruleName.contains("per day") || ruleName.contains("daily")) {
            return CalculationType.PER_DAY;
        }
        return CalculationType.ONE_TIME;
    }
}