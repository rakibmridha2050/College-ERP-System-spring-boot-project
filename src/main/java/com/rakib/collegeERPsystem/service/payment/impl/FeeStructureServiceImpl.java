package com.rakib.collegeERPsystem.service.payment.impl;

import com.rakib.collegeERPsystem.dto.payment.FeeComponentDTO;
import com.rakib.collegeERPsystem.dto.payment.FeeStructureDTO;
import com.rakib.collegeERPsystem.entity.payment.FeeStructure;
import com.rakib.collegeERPsystem.enums.FeeStatus;
import com.rakib.collegeERPsystem.repository.payment.FeeStructureRepository;
import com.rakib.collegeERPsystem.service.payment.FeeStructureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeeStructureServiceImpl implements FeeStructureService {

    private final FeeStructureRepository repository;

    @Override
    public FeeStructureDTO createFeeStructure(FeeStructureDTO dto) {
        FeeStructure entity = mapToEntity(dto);
        entity.setStatus(FeeStatus.ACTIVE); // default status
        entity.setCreatedAt(LocalDateTime.now());
        entity.setActive(true);

        FeeStructure saved = repository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public FeeStructureDTO updateFeeStructure(Long id, FeeStructureDTO dto) {
        FeeStructure entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fee Structure not found"));

        entity.setProgram(dto.getProgram());
        entity.setSemester(dto.getSemester());
        entity.setStudentCategory(dto.getStudentCategory());
        entity.setAcademicSession(dto.getAcademicSession());
        entity.setTotalAmount(dto.getTotalAmount());
        entity.setActive(dto.getActive());
        entity.setUpdatedAt(LocalDateTime.now());

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
        entity.setActive(false);
        repository.save(entity);
    }

    // ✅ Convert Entity → DTO
    private FeeStructureDTO mapToDTO(FeeStructure entity) {
        return FeeStructureDTO.builder()
                .id(entity.getId())
                .program(entity.getProgram())
                .semester(entity.getSemester())
                .studentCategory(entity.getStudentCategory())
                .academicSession(entity.getAcademicSession())
                .totalAmount(entity.getTotalAmount())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .createdBy(entity.getCreatedBy())
                .updatedBy(entity.getUpdatedBy())
                .active(entity.getActive())
                .build();
    }

    // ✅ Convert DTO → Entity
    private FeeStructure mapToEntity(FeeStructureDTO dto) {
        FeeStructure entity = new FeeStructure();
        entity.setProgram(dto.getProgram());
        entity.setSemester(dto.getSemester());
        entity.setStudentCategory(dto.getStudentCategory());
        entity.setAcademicSession(dto.getAcademicSession());
        entity.setTotalAmount(dto.getTotalAmount());
        entity.setStatus(dto.getStatus() != null ? dto.getStatus() : FeeStatus.ACTIVE);
        entity.setActive(dto.getActive() != null ? dto.getActive() : true);
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setUpdatedBy(dto.getUpdatedBy());
        return entity;
    }
}
