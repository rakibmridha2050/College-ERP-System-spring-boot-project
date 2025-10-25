package com.rakib.collegeERPsystem.service.payment.impl;

import com.rakib.collegeERPsystem.dto.payment.FeeComponentDTO;
import com.rakib.collegeERPsystem.entity.payment.FeeComponent;
import com.rakib.collegeERPsystem.entity.payment.FeeStructure;
import com.rakib.collegeERPsystem.repository.payment.FeeComponentRepository;
import com.rakib.collegeERPsystem.repository.payment.FeeStructureRepository;
import com.rakib.collegeERPsystem.service.payment.FeeComponentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeeComponentServiceImpl implements FeeComponentService {

    private final FeeComponentRepository feeComponentRepository;
    private final FeeStructureRepository feeStructureRepository;

    @Override
    public FeeComponentDTO createFeeComponent(Long feeStructureId, FeeComponentDTO dto) {
        FeeStructure structure = feeStructureRepository.findById(feeStructureId)
                .orElseThrow(() -> new RuntimeException("Fee Structure not found"));

        FeeComponent component = new FeeComponent();
        BeanUtils.copyProperties(dto, component);
        component.setFeeStructure(structure);
        FeeComponent saved = feeComponentRepository.save(component);

        return mapToDTO(saved);
    }

    @Override
    public FeeComponentDTO updateFeeComponent(Long id, FeeComponentDTO dto) {
        FeeComponent existing = feeComponentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fee Component not found"));

        existing.setName(dto.getComponentName());
        existing.setAmount(dto.getAmount());
        existing.setDescription(dto.getDescription());
        existing.setIsActive(dto.getIsActive());

        FeeComponent updated = feeComponentRepository.save(existing);
        return mapToDTO(updated);
    }

    @Override
    public FeeComponentDTO getFeeComponentById(Long id) {
        FeeComponent entity = feeComponentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fee Component not found"));
        return mapToDTO(entity);
    }

    @Override
    public List<FeeComponentDTO> getComponentsByFeeStructure(Long feeStructureId) {
        return feeComponentRepository.findByFeeStructureId(feeStructureId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteFeeComponent(Long id) {
        FeeComponent entity = feeComponentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fee Component not found"));
        entity.setIsActive(false); // Soft delete
        feeComponentRepository.save(entity);
    }

    private FeeComponentDTO mapToDTO(FeeComponent component) {
        return FeeComponentDTO.builder()
                .id(component.getId())
                .componentName(component.getName())
                .amount(component.getAmount())
                .description(component.getDescription())
                .isActive(component.getIsActive())
                .build();
    }
}
