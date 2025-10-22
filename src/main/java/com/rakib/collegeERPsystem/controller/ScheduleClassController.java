package com.rakib.collegeERPsystem.controller;

import com.rakib.collegeERPsystem.dto.ScheduleClassDTO;
import com.rakib.collegeERPsystem.service.ScheduleClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule-classes")
@RequiredArgsConstructor
public class ScheduleClassController {

    private final ScheduleClassService service;

    @PostMapping
    public ResponseEntity<ScheduleClassDTO> create(@RequestBody ScheduleClassDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<ScheduleClassDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleClassDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleClassDTO> update(@PathVariable Long id, @RequestBody ScheduleClassDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

