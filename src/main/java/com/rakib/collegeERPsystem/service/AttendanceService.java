package com.rakib.collegeERPsystem.service;

import com.rakib.collegeERPsystem.dtos.AttendanceRequestDTO;
import com.rakib.collegeERPsystem.dtos.AttendanceResponseDTO;
import com.rakib.collegeERPsystem.entity.*;
import com.rakib.collegeERPsystem.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentCourseEnrollmentRepository enrollmentRepository;
    private final FacultyRepository facultyRepository;
    private final ClassesRepository classesRepository;
    private final SectionRepository sectionRepository;

    // Create attendance
    public AttendanceResponseDTO createAttendance(AttendanceRequestDTO requestDTO) {
        Attendance attendance = Attendance.builder()
                .enrollment(enrollmentRepository.findById(requestDTO.getEnrollmentId())
                        .orElseThrow(() -> new RuntimeException("Enrollment not found")))
                .faculty(facultyRepository.findById(requestDTO.getFacultyId())
                        .orElseThrow(() -> new RuntimeException("Faculty not found")))
                .classes(requestDTO.getClassId() != null ?
                        classesRepository.findById(requestDTO.getClassId()).orElse(null) : null)
                .section(requestDTO.getSectionId() != null ?
                        sectionRepository.findById(requestDTO.getSectionId()).orElse(null) : null)
                .attendanceDate(requestDTO.getAttendanceDate() != null ? requestDTO.getAttendanceDate() : java.time.LocalDateTime.now())
                .status(requestDTO.getStatus())
                .build();

        Attendance saved = attendanceRepository.save(attendance);
        return mapToDTO(saved);
    }

    // Get attendance by ID
    public AttendanceResponseDTO getAttendanceById(Long id) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));
        return mapToDTO(attendance);
    }

    // Get all attendances
    public List<AttendanceResponseDTO> getAllAttendances() {
        return attendanceRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Update attendance
    public AttendanceResponseDTO updateAttendance(Long id, AttendanceRequestDTO requestDTO) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        // Update fields
        attendance.setEnrollment(enrollmentRepository.findById(requestDTO.getEnrollmentId())
                .orElseThrow(() -> new RuntimeException("Enrollment not found")));

        attendance.setFaculty(facultyRepository.findById(requestDTO.getFacultyId())
                .orElseThrow(() -> new RuntimeException("Faculty not found")));

        attendance.setClasses(requestDTO.getClassId() != null ?
                classesRepository.findById(requestDTO.getClassId()).orElse(null) : null);

        attendance.setSection(requestDTO.getSectionId() != null ?
                sectionRepository.findById(requestDTO.getSectionId()).orElse(null) : null);

        attendance.setAttendanceDate(requestDTO.getAttendanceDate() != null ? requestDTO.getAttendanceDate() : java.time.LocalDateTime.now());
        attendance.setStatus(requestDTO.getStatus());

        Attendance updated = attendanceRepository.save(attendance);
        return mapToDTO(updated);
    }

    // Delete attendance
    public void deleteAttendance(Long id) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));
        attendanceRepository.delete(attendance);
    }

    // Mapping Attendance entity to DTO
    private AttendanceResponseDTO mapToDTO(Attendance attendance) {
        return AttendanceResponseDTO.builder()
                .id(attendance.getId())
                .enrollmentId(attendance.getEnrollment().getEnrollmentId())
                .studentName(attendance.getEnrollment().getStudent().getName())
                .facultyId(attendance.getFaculty().getId())
                .facultyName(attendance.getFaculty().getName())
                .classId(attendance.getClasses() != null ? attendance.getClasses().getId() : null)
                .className(attendance.getClasses() != null ? attendance.getClasses().getClassName() : null)
                .sectionId(attendance.getSection() != null ? attendance.getSection().getId() : null)
                .sectionName(attendance.getSection() != null ? attendance.getSection().getSectionName() : null)
                .attendanceDate(attendance.getAttendanceDate())
                .status(attendance.getStatus())
                .build();
    }
}
