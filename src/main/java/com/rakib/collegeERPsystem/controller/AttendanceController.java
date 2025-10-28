package com.rakib.collegeERPsystem.controller;


import com.rakib.collegeERPsystem.dto.AttendanceRequestDTO;
import com.rakib.collegeERPsystem.entity.Attendance;
import com.rakib.collegeERPsystem.service.AttendanceService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
    @RequestMapping("/api/attendances")
    @CrossOrigin(origins = "http://localhost:4200") // allow Angular dev server
    public class AttendanceController {

        private final AttendanceService attendanceService;

        public AttendanceController(AttendanceService attendanceService) {
            this.attendanceService = attendanceService;
        }

        @PostMapping
        public ResponseEntity<Attendance> create(@RequestBody AttendanceRequestDTO dto) {
            return ResponseEntity.ok(attendanceService.saveAttendance(dto));
        }

        @PostMapping("/bulk")
        public ResponseEntity<List<Attendance>> createBulk(@RequestBody List<AttendanceRequestDTO> dtos) {
            return ResponseEntity.ok(attendanceService.saveBulkAttendance(dtos));
        }

        @GetMapping("/section/{sectionId}")
        public ResponseEntity<List<Attendance>> getBySectionAndDate(
                @PathVariable Long sectionId,
                @RequestParam(required = false) String date) {

            LocalDate targetDate = (date != null) ? LocalDate.parse(date) : LocalDate.now();
            return ResponseEntity.ok(attendanceService.getAttendanceBySectionAndDate(sectionId, targetDate));
        }
    }

