package com.rakib.collegeERPsystem.service.payment.impl;

import com.rakib.collegeERPsystem.dto.payment.FeeWaiverDTO;
import com.rakib.collegeERPsystem.entity.Student;
import com.rakib.collegeERPsystem.entity.payment.FeeWaiver;
import com.rakib.collegeERPsystem.enums.WaiverStatus;
import com.rakib.collegeERPsystem.enums.WaiverType;
import com.rakib.collegeERPsystem.repository.StudentRepository;
import com.rakib.collegeERPsystem.repository.payment.FeeWaiverRepository;
import com.rakib.collegeERPsystem.service.payment.FeeWaiverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeeWaiverServiceImpl implements FeeWaiverService {

    private final FeeWaiverRepository feeWaiverRepository;
    private final StudentRepository studentRepository;

    @Override
    @Transactional
    public FeeWaiverDTO createFeeWaiver(FeeWaiverDTO feeWaiverDTO) {
        try {
            // Check if student exists
            Student student = studentRepository.findById(feeWaiverDTO.getStudentId())
                    .orElseThrow(() -> new RuntimeException("Student not found with id: " + feeWaiverDTO.getStudentId()));

            // Check if student already has a pending waiver for the same semester
            if (hasPendingWaiverInSemester(feeWaiverDTO.getStudentId(), getCurrentSemester())) {
                throw new RuntimeException("Student already has a pending fee waiver for this semester");
            }

            FeeWaiver feeWaiver = FeeWaiver.builder()
                    .student(student)
                    .waiverType(WaiverType.NEED_BASED) // You might want to get this from DTO
                    .description(feeWaiverDTO.getReason())
                    .waiverPercentage(feeWaiverDTO.getWaiverPercentage())
                    .waiverAmount(feeWaiverDTO.getWaiverAmount())
                    .applicableSemester(getCurrentSemester())
                    .status(WaiverStatus.PENDING)
                    .build();

            FeeWaiver savedWaiver = feeWaiverRepository.save(feeWaiver);
            log.info("Fee waiver created successfully for student ID: {}", feeWaiverDTO.getStudentId());

            return convertToDTO(savedWaiver);
        } catch (Exception e) {
            log.error("Error creating fee waiver: {}", e.getMessage());
            throw new RuntimeException("Failed to create fee waiver: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public FeeWaiverDTO updateFeeWaiver(Long id, FeeWaiverDTO feeWaiverDTO) {
        try {
            FeeWaiver existingWaiver = feeWaiverRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Fee waiver not found with id: " + id));

            // Only allow updates to pending waivers
            if (existingWaiver.getStatus() != WaiverStatus.PENDING) {
                throw new RuntimeException("Cannot update fee waiver with status: " + existingWaiver.getStatus());
            }

            if (feeWaiverDTO.getWaiverPercentage() != null) {
                existingWaiver.setWaiverPercentage(feeWaiverDTO.getWaiverPercentage());
            }
            if (feeWaiverDTO.getWaiverAmount() != null) {
                existingWaiver.setWaiverAmount(feeWaiverDTO.getWaiverAmount());
            }
            if (feeWaiverDTO.getReason() != null) {
                existingWaiver.setDescription(feeWaiverDTO.getReason());
            }

            FeeWaiver updatedWaiver = feeWaiverRepository.save(existingWaiver);
            log.info("Fee waiver updated successfully with ID: {}", id);

            return convertToDTO(updatedWaiver);
        } catch (Exception e) {
            log.error("Error updating fee waiver with ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to update fee waiver: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public FeeWaiverDTO approveFeeWaiver(Long id, String approvedBy) {
        try {
            FeeWaiver feeWaiver = feeWaiverRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Fee waiver not found with id: " + id));

            if (feeWaiver.getStatus() != WaiverStatus.PENDING) {
                throw new RuntimeException("Fee waiver is not in PENDING status");
            }

            feeWaiver.setStatus(WaiverStatus.APPROVED);
            feeWaiver.setApprovedBy(approvedBy);
            feeWaiver.setApprovedDate(LocalDate.now());

            FeeWaiver approvedWaiver = feeWaiverRepository.save(feeWaiver);
            log.info("Fee waiver approved successfully with ID: {}", id);

            return convertToDTO(approvedWaiver);
        } catch (Exception e) {
            log.error("Error approving fee waiver with ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to approve fee waiver: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public FeeWaiverDTO rejectFeeWaiver(Long id, String rejectedBy) {
        try {
            FeeWaiver feeWaiver = feeWaiverRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Fee waiver not found with id: " + id));

            if (feeWaiver.getStatus() != WaiverStatus.PENDING) {
                throw new RuntimeException("Fee waiver is not in PENDING status");
            }

            feeWaiver.setStatus(WaiverStatus.REJECTED);
            feeWaiver.setApprovedBy(rejectedBy);
            feeWaiver.setApprovedDate(LocalDate.now());

            FeeWaiver rejectedWaiver = feeWaiverRepository.save(feeWaiver);
            log.info("Fee waiver rejected successfully with ID: {}", id);

            return convertToDTO(rejectedWaiver);
        } catch (Exception e) {
            log.error("Error rejecting fee waiver with ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to reject fee waiver: " + e.getMessage());
        }
    }

    @Override
    public FeeWaiverDTO getFeeWaiverById(Long id) {
        FeeWaiver feeWaiver = feeWaiverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fee waiver not found with id: " + id));
        return convertToDTO(feeWaiver);
    }

    @Override
    public List<FeeWaiverDTO> getAllFeeWaivers() {
        return feeWaiverRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FeeWaiverDTO> getFeeWaiversByStudentId(Long studentId) {
        return feeWaiverRepository.findByStudentId(studentId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FeeWaiverDTO> getFeeWaiversByStatus(WaiverStatus status) {
        return feeWaiverRepository.findByStatus(status)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteFeeWaiver(Long id) {
        try {
            FeeWaiver feeWaiver = feeWaiverRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Fee waiver not found with id: " + id));

            // Only allow deletion of pending waivers
            if (feeWaiver.getStatus() != WaiverStatus.PENDING) {
                throw new RuntimeException("Cannot delete fee waiver with status: " + feeWaiver.getStatus());
            }

            feeWaiverRepository.delete(feeWaiver);
            log.info("Fee waiver deleted successfully with ID: {}", id);
        } catch (Exception e) {
            log.error("Error deleting fee waiver with ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to delete fee waiver: " + e.getMessage());
        }
    }

    @Override
    public Double getTotalApprovedWaiverAmount(Long studentId) {
        return feeWaiverRepository.getTotalApprovedWaiverAmountByStudentId(studentId);
    }

    @Override
    public boolean hasPendingWaiverInSemester(Long studentId, int semester) {
        return feeWaiverRepository.existsByStudentIdAndApplicableSemester(studentId, semester);
    }

    private FeeWaiverDTO convertToDTO(FeeWaiver feeWaiver) {
        return FeeWaiverDTO.builder()
                .id(feeWaiver.getId())
                .studentId(feeWaiver.getStudent().getId())
                .studentName(feeWaiver.getStudent().getName())
                .reason(feeWaiver.getDescription())
                .waiverPercentage(feeWaiver.getWaiverPercentage())
                .waiverAmount(feeWaiver.getWaiverAmount())
                .appliedDate(feeWaiver.getCreatedAt() != null ? feeWaiver.getCreatedAt().toLocalDate() : null)
                .approvedBy(feeWaiver.getApprovedBy())
                .build();
    }

    private int getCurrentSemester() {
        // This should be implemented based on your semester calculation logic
        // For now, returning a default value
        return 1; // You might want to get this from system configuration or current date
    }
}