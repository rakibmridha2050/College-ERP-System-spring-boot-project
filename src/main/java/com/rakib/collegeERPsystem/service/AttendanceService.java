package com.rakib.collegeERPsystem.service;


import com.rakib.collegeERPsystem.dto.AttendanceRequestDTO;
import com.rakib.collegeERPsystem.entity.Attendance;
import com.rakib.collegeERPsystem.repository.AttendanceRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public Attendance saveAttendance(AttendanceRequestDTO dto) {
        Attendance attendance = new Attendance(
                dto.getEnrollmentId(),
                dto.getFacultyId(),
                dto.getClassId(),
                dto.getSectionId(),
                dto.getAttendanceDate(),
                dto.getStatus()
        );
        return attendanceRepository.save(attendance);
    }

    public List<Attendance> saveBulkAttendance(List<AttendanceRequestDTO> dtos) {
        List<Attendance> attendances = dtos.stream().map(dto -> new Attendance(
                dto.getEnrollmentId(),
                dto.getFacultyId(),
                dto.getClassId(),
                dto.getSectionId(),
                dto.getAttendanceDate(),
                dto.getStatus()
        )).collect(Collectors.toList());

        return attendanceRepository.saveAll(attendances);
    }

    public List<Attendance> getAttendanceBySectionAndDate(Long sectionId, LocalDate date) {
        return attendanceRepository.findBySectionIdAndAttendanceDate(sectionId, date);
    }
}