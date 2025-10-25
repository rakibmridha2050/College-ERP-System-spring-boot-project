package com.rakib.collegeERPsystem.repository.payment;

import com.rakib.collegeERPsystem.entity.payment.FeeComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeeComponentRepository extends JpaRepository<FeeComponent, Long> {

    // Find all components linked to a FeeStructure
    List<FeeComponent> findByFeeStructureId(Long feeStructureId);

    // Optional filter for active components
    List<FeeComponent> findByFeeStructureIdAndIsActiveTrue(Long feeStructureId);
}
