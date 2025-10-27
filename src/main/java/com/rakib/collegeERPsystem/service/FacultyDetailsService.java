package com.rakib.collegeERPsystem.service;

import com.rakib.collegeERPsystem.dto.FacultyDetailsRequestDTO;
import com.rakib.collegeERPsystem.dto.FacultyDetailsResponseDTO;
import com.rakib.collegeERPsystem.entity.Address;
import com.rakib.collegeERPsystem.entity.Faculty;
import com.rakib.collegeERPsystem.entity.FacultyDetails;
import com.rakib.collegeERPsystem.repository.FacultyDetailsRepository;
import com.rakib.collegeERPsystem.repository.FacultyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FacultyDetailsService {

    private final FacultyDetailsRepository facultyDetailsRepository;
    private final FacultyRepository facultyRepository;

    @Transactional
    public FacultyDetailsResponseDTO createFacultyDetails(FacultyDetailsRequestDTO requestDTO) {
        try {
            // Check if faculty exists
            Faculty faculty = facultyRepository.findById(requestDTO.getFacultyId())
                    .orElseThrow(() -> new RuntimeException("Faculty not found with id: " + requestDTO.getFacultyId()));

            // Check if faculty details already exist
            if (facultyDetailsRepository.existsByFacultyId(requestDTO.getFacultyId())) {
                throw new RuntimeException("Faculty details already exist for faculty id: " + requestDTO.getFacultyId());
            }

            // Check if employee ID is unique
            if (facultyDetailsRepository.existsByEmployeeId(requestDTO.getEmployeeId())) {
                throw new RuntimeException("Employee ID already exists: " + requestDTO.getEmployeeId());
            }

            // Build address
            Address address = Address.builder()
                    .streetAddress(requestDTO.getStreetAddress())
                    .city(requestDTO.getCity())
                    .state(requestDTO.getState())
                    .country(requestDTO.getCountry())
                    .postalCode(requestDTO.getPostalCode())
                    .build();

            // Build faculty details
            FacultyDetails facultyDetails = FacultyDetails.builder()
                    .faculty(faculty)
                    .employeeId(requestDTO.getEmployeeId())
                    .dateOfBirth(requestDTO.getDateOfBirth())
                    .gender(requestDTO.getGender())
                    .bloodGroup(requestDTO.getBloodGroup())
                    .nationalId(requestDTO.getNationalId())
                    .passportNumber(requestDTO.getPassportNumber())
                    .address(address)
                    .emergencyContact(requestDTO.getEmergencyContact())
                    .emergencyContactRelationship(requestDTO.getEmergencyContactRelationship())
                    .emergencyContactPhone(requestDTO.getEmergencyContactPhone())
                    .bankAccountNumber(requestDTO.getBankAccountNumber())
                    .bankName(requestDTO.getBankName())
                    .bankBranch(requestDTO.getBankBranch())
                    .joiningDate(requestDTO.getJoiningDate())
                    .qualification(requestDTO.getQualification())
                    .specialization(requestDTO.getSpecialization())
                    .experienceYears(requestDTO.getExperienceYears())
                    .salaryScale(requestDTO.getSalaryScale())
                    .currentSalary(requestDTO.getCurrentSalary())
                    .maritalStatus(requestDTO.getMaritalStatus())
                    .spouseName(requestDTO.getSpouseName())
                    .profilePictureUrl(requestDTO.getProfilePictureUrl())
                    .cvDocumentUrl(requestDTO.getCvDocumentUrl())
                    .otherDocumentsUrls(requestDTO.getOtherDocumentsUrls())
                    .bio(requestDTO.getBio())
                    .researchInterests(requestDTO.getResearchInterests())
                    .publications(requestDTO.getPublications())
                    .awardsAchievements(requestDTO.getAwardsAchievements())
                    .build();

            FacultyDetails savedDetails = facultyDetailsRepository.save(facultyDetails);
            log.info("Faculty details created successfully for faculty id: {}", requestDTO.getFacultyId());

            return convertToResponseDTO(savedDetails);
        } catch (Exception e) {
            log.error("Error creating faculty details: {}", e.getMessage());
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public FacultyDetailsResponseDTO getFacultyDetailsById(Long id) {
        FacultyDetails facultyDetails = facultyDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faculty details not found with id: " + id));
        return convertToResponseDTO(facultyDetails);
    }

    @Transactional(readOnly = true)
    public FacultyDetailsResponseDTO getFacultyDetailsByFacultyId(Long facultyId) {
        FacultyDetails facultyDetails = facultyDetailsRepository.findByFacultyId(facultyId)
                .orElseThrow(() -> new RuntimeException("Faculty details not found for faculty id: " + facultyId));
        return convertToResponseDTO(facultyDetails);
    }

    @Transactional(readOnly = true)
    public List<FacultyDetailsResponseDTO> getAllFacultyDetails() {
        return facultyDetailsRepository.findByActiveTrue()
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FacultyDetailsResponseDTO> getFacultyDetailsByDepartment(Long departmentId) {
        return facultyDetailsRepository.findByDepartmentId(departmentId)
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public FacultyDetailsResponseDTO updateFacultyDetails(Long id, FacultyDetailsRequestDTO requestDTO) {
        try {
            FacultyDetails existingDetails = facultyDetailsRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Faculty details not found with id: " + id));

            // Update basic fields
            if (requestDTO.getDateOfBirth() != null) {
                existingDetails.setDateOfBirth(requestDTO.getDateOfBirth());
            }
            if (requestDTO.getGender() != null) {
                existingDetails.setGender(requestDTO.getGender());
            }
            if (requestDTO.getBloodGroup() != null) {
                existingDetails.setBloodGroup(requestDTO.getBloodGroup());
            }
            if (requestDTO.getNationalId() != null) {
                existingDetails.setNationalId(requestDTO.getNationalId());
            }
            if (requestDTO.getPassportNumber() != null) {
                existingDetails.setPassportNumber(requestDTO.getPassportNumber());
            }

            // Update address
            if (requestDTO.getStreetAddress() != null || requestDTO.getCity() != null ||
                    requestDTO.getState() != null || requestDTO.getCountry() != null ||
                    requestDTO.getPostalCode() != null) {

                Address address = existingDetails.getAddress() != null ?
                        existingDetails.getAddress() : new Address();

                if (requestDTO.getStreetAddress() != null) {
                    address.setStreetAddress(requestDTO.getStreetAddress());
                }
                if (requestDTO.getCity() != null) {
                    address.setCity(requestDTO.getCity());
                }
                if (requestDTO.getState() != null) {
                    address.setState(requestDTO.getState());
                }
                if (requestDTO.getCountry() != null) {
                    address.setCountry(requestDTO.getCountry());
                }
                if (requestDTO.getPostalCode() != null) {
                    address.setPostalCode(requestDTO.getPostalCode());
                }

                existingDetails.setAddress(address);
            }

            // Update other fields
            if (requestDTO.getEmergencyContact() != null) {
                existingDetails.setEmergencyContact(requestDTO.getEmergencyContact());
            }
            if (requestDTO.getEmergencyContactRelationship() != null) {
                existingDetails.setEmergencyContactRelationship(requestDTO.getEmergencyContactRelationship());
            }
            if (requestDTO.getEmergencyContactPhone() != null) {
                existingDetails.setEmergencyContactPhone(requestDTO.getEmergencyContactPhone());
            }
            if (requestDTO.getBankAccountNumber() != null) {
                existingDetails.setBankAccountNumber(requestDTO.getBankAccountNumber());
            }
            if (requestDTO.getBankName() != null) {
                existingDetails.setBankName(requestDTO.getBankName());
            }
            if (requestDTO.getBankBranch() != null) {
                existingDetails.setBankBranch(requestDTO.getBankBranch());
            }
            if (requestDTO.getQualification() != null) {
                existingDetails.setQualification(requestDTO.getQualification());
            }
            if (requestDTO.getSpecialization() != null) {
                existingDetails.setSpecialization(requestDTO.getSpecialization());
            }
            if (requestDTO.getExperienceYears() != null) {
                existingDetails.setExperienceYears(requestDTO.getExperienceYears());
            }
            if (requestDTO.getSalaryScale() != null) {
                existingDetails.setSalaryScale(requestDTO.getSalaryScale());
            }
            if (requestDTO.getCurrentSalary() != null) {
                existingDetails.setCurrentSalary(requestDTO.getCurrentSalary());
            }
            if (requestDTO.getMaritalStatus() != null) {
                existingDetails.setMaritalStatus(requestDTO.getMaritalStatus());
            }
            if (requestDTO.getSpouseName() != null) {
                existingDetails.setSpouseName(requestDTO.getSpouseName());
            }
            if (requestDTO.getProfilePictureUrl() != null) {
                existingDetails.setProfilePictureUrl(requestDTO.getProfilePictureUrl());
            }
            if (requestDTO.getCvDocumentUrl() != null) {
                existingDetails.setCvDocumentUrl(requestDTO.getCvDocumentUrl());
            }
            if (requestDTO.getOtherDocumentsUrls() != null) {
                existingDetails.setOtherDocumentsUrls(requestDTO.getOtherDocumentsUrls());
            }
            if (requestDTO.getBio() != null) {
                existingDetails.setBio(requestDTO.getBio());
            }
            if (requestDTO.getResearchInterests() != null) {
                existingDetails.setResearchInterests(requestDTO.getResearchInterests());
            }
            if (requestDTO.getPublications() != null) {
                existingDetails.setPublications(requestDTO.getPublications());
            }
            if (requestDTO.getAwardsAchievements() != null) {
                existingDetails.setAwardsAchievements(requestDTO.getAwardsAchievements());
            }

            FacultyDetails updatedDetails = facultyDetailsRepository.save(existingDetails);
            log.info("Faculty details updated successfully for id: {}", id);

            return convertToResponseDTO(updatedDetails);
        } catch (Exception e) {
            log.error("Error updating faculty details: {}", e.getMessage());
            throw e;
        }
    }

    @Transactional
    public void deleteFacultyDetails(Long id) {
        FacultyDetails facultyDetails = facultyDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faculty details not found with id: " + id));

        facultyDetails.setActive(false);
        facultyDetailsRepository.save(facultyDetails);
        log.info("Faculty details soft deleted with id: {}", id);
    }

    @Transactional
    public void permanentDeleteFacultyDetails(Long id) {
        if (!facultyDetailsRepository.existsById(id)) {
            throw new RuntimeException("Faculty details not found with id: " + id);
        }
        facultyDetailsRepository.deleteById(id);
        log.info("Faculty details permanently deleted with id: {}", id);
    }

    // Helper method to convert entity to response DTO
    private FacultyDetailsResponseDTO convertToResponseDTO(FacultyDetails facultyDetails) {
        Address address = facultyDetails.getAddress();

        return FacultyDetailsResponseDTO.builder()
                .id(facultyDetails.getId())
                .facultyId(facultyDetails.getFaculty().getId())
                .facultyName(facultyDetails.getFaculty().getName())
                .facultyEmail(facultyDetails.getFaculty().getEmail())
                .employeeId(facultyDetails.getEmployeeId())
                .dateOfBirth(facultyDetails.getDateOfBirth())
                .gender(facultyDetails.getGender())
                .bloodGroup(facultyDetails.getBloodGroup())
                .nationalId(facultyDetails.getNationalId())
                .passportNumber(facultyDetails.getPassportNumber())
                .streetAddress(address != null ? address.getStreetAddress() : null)
                .city(address != null ? address.getCity() : null)
                .state(address != null ? address.getState() : null)
                .country(address != null ? address.getCountry() : null)
                .postalCode(address != null ? address.getPostalCode() : null)
                .emergencyContact(facultyDetails.getEmergencyContact())
                .emergencyContactRelationship(facultyDetails.getEmergencyContactRelationship())
                .emergencyContactPhone(facultyDetails.getEmergencyContactPhone())
                .bankAccountNumber(facultyDetails.getBankAccountNumber())
                .bankName(facultyDetails.getBankName())
                .bankBranch(facultyDetails.getBankBranch())
                .joiningDate(facultyDetails.getJoiningDate())
                .qualification(facultyDetails.getQualification())
                .specialization(facultyDetails.getSpecialization())
                .experienceYears(facultyDetails.getExperienceYears())
                .salaryScale(facultyDetails.getSalaryScale())
                .currentSalary(facultyDetails.getCurrentSalary())
                .maritalStatus(facultyDetails.getMaritalStatus())
                .spouseName(facultyDetails.getSpouseName())
                .profilePictureUrl(facultyDetails.getProfilePictureUrl())
                .cvDocumentUrl(facultyDetails.getCvDocumentUrl())
                .otherDocumentsUrls(facultyDetails.getOtherDocumentsUrls())
                .bio(facultyDetails.getBio())
                .researchInterests(facultyDetails.getResearchInterests())
                .publications(facultyDetails.getPublications())
                .awardsAchievements(facultyDetails.getAwardsAchievements())
                .createdAt(facultyDetails.getCreatedAt())
                .updatedAt(facultyDetails.getUpdatedAt())
                .active(facultyDetails.getActive())
                .createdBy(facultyDetails.getCreatedBy())
                .updatedBy(facultyDetails.getUpdatedBy())
                .build();
    }
}