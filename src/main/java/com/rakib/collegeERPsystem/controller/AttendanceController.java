package com.rakib.collegeERPsystem.controller;

import com.rakib.collegeERPsystem.dto.AttendanceRequestDTO;
import com.rakib.collegeERPsystem.dto.AttendanceResponseDTO;
import com.rakib.collegeERPsystem.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendances")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    // Create attendance
    @PostMapping
    public ResponseEntity<AttendanceResponseDTO> createAttendance(@RequestBody AttendanceRequestDTO requestDTO) {
        AttendanceResponseDTO responseDTO = attendanceService.createAttendance(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    // Get attendance by ID
    @GetMapping("/{id}")
    public ResponseEntity<AttendanceResponseDTO> getAttendanceById(@PathVariable Long id) {
        AttendanceResponseDTO responseDTO = attendanceService.getAttendanceById(id);
        return ResponseEntity.ok(responseDTO);
    }

    // Get all attendances
    @GetMapping
    public ResponseEntity<List<AttendanceResponseDTO>> getAllAttendances() {
        List<AttendanceResponseDTO> list = attendanceService.getAllAttendances();
        return ResponseEntity.ok(list);
    }

    // Update attendance
    @PutMapping("/{id}")
    public ResponseEntity<AttendanceResponseDTO> updateAttendance(@PathVariable Long id,
                                                                  @RequestBody AttendanceRequestDTO requestDTO) {
        AttendanceResponseDTO responseDTO = attendanceService.updateAttendance(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    // Delete attendance
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
        return ResponseEntity.noContent().build();
    }
}
