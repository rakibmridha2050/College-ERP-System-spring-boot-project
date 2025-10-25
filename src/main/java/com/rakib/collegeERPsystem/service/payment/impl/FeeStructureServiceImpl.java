package com.rakib.collegeERPsystem.service.payment.impl;

import com.rakib.collegeERPsystem.dto.payment.FeeStructureDTO;
import com.rakib.collegeERPsystem.entity.payment.FeeStructure;
import com.rakib.collegeERPsystem.enums.FeeStatus;
import com.rakib.collegeERPsystem.repository.payment.FeeStructureRepository;
import com.rakib.collegeERPsystem.service.payment.FeeStructureService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeeStructureServiceImpl implements FeeStructureService {

    private final FeeStructureRepository repository;

    @Override
    public FeeStructureDTO createFeeStructure(FeeStructureDTO dto) {
        FeeStructure entity = new FeeStructure();
        BeanUtils.copyProperties(dto, entity);
        entity.setStatus(FeeStatus.ACTIVE);
        FeeStructure saved = repository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public FeeStructureDTO updateFeeStructure(Long id, FeeStructureDTO dto) {
        FeeStructure entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fee Structure not found"));

        BeanUtils.copyProperties(dto, entity, "id");
        FeeStructure updated = repository.save(entity);
        return mapToDTO(updated);
    }

    @Override
    public FeeStructureDTO getFeeStructureById(Long id) {
        FeeStructure entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fee Structure not found"));
        return mapToDTO(entity);
    }

    @Override
    public List<FeeStructureDTO> getAllFeeStructures() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteFeeStructure(Long id) {
        FeeStructure entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fee Structure not found"));
        entity.setStatus(FeeStatus.INACTIVE);
        repository.save(entity);
    }

    // Mapping helper
    private FeeStructureDTO mapToDTO(FeeStructure entity) {
        return FeeStructureDTO.builder()
                .id(entity.getId())
                .structureName(entity.getProgram() + " - Semester " + entity.getSemester())
                .academicYear(entity.getAcademicSession())
                .semester(String.valueOf(entity.getSemester()))
                .isActive(entity.getStatus() == FeeStatus.ACTIVE)
                .build();
    }
}
