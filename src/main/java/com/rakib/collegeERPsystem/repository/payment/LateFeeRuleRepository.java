package com.rakib.collegeERPsystem.repository.payment;

import com.rakib.collegeERPsystem.entity.payment.LateFeeRule;
import com.rakib.collegeERPsystem.enums.CalculationType;
import com.rakib.collegeERPsystem.enums.FineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LateFeeRuleRepository extends JpaRepository<LateFeeRule, Long> {

    // Find all active late fee rules
    List<LateFeeRule> findByIsActiveTrue();

    // Find late fee rules by fine type
    List<LateFeeRule> findByFineType(FineType fineType);

    // Find late fee rules by calculation type
    List<LateFeeRule> findByCalculationType(CalculationType calculationType);

    // Find active late fee rules by calculation type
    List<LateFeeRule> findByCalculationTypeAndIsActiveTrue(CalculationType calculationType);

    // Check if rule name already exists (excluding current rule for updates)
    boolean existsByNameAndIdNot(String name, Long id);

    // Check if rule name already exists
    boolean existsByName(String name);

    // Find the first active rule (for default application)
    @Query("SELECT lfr FROM LateFeeRule lfr WHERE lfr.isActive = true ORDER BY lfr.gracePeriodInDays ASC")
    List<LateFeeRule> findActiveRulesOrderByGracePeriod();

    // Find rules with grace period less than or equal to given days
    List<LateFeeRule> findByGracePeriodInDaysLessThanEqualAndIsActiveTrue(int days);
}