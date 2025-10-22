package com.rakib.collegeERPsystem.service;


import com.rakib.collegeERPsystem.dto.ScheduleClassDTO;
import com.rakib.collegeERPsystem.entity.ScheduleClass;
import com.rakib.collegeERPsystem.repository.ScheduleClassRepository;
import com.rakib.collegeERPsystem.service.ScheduleClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleClassServiceImpl implements ScheduleClassService {

    private final ScheduleClassRepository repository;

    private ScheduleClassDTO mapToDTO(ScheduleClass entity) {
        return ScheduleClassDTO.builder()
                .id(entity.getId())
                .type(entity.getType())
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .status(entity.getStatus())
                .build();
    }

    private ScheduleClass mapToEntity(ScheduleClassDTO dto) {
        return ScheduleClass.builder()
                .type(dto.getType())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .status(dto.getStatus())
                .build();
    }

    @Override
    public ScheduleClassDTO create(ScheduleClassDTO dto) {
        ScheduleClass entity = repository.save(mapToEntity(dto));
        return mapToDTO(entity);
    }

    @Override
    public List<ScheduleClassDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleClassDTO getById(Long id) {
        ScheduleClass entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ScheduleClass not found with id: " + id));
        return mapToDTO(entity);
    }

    @Override
    public ScheduleClassDTO update(Long id, ScheduleClassDTO dto) {
        ScheduleClass entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ScheduleClass not found with id: " + id));

        entity.setType(dto.getType());
        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());
        entity.setStatus(dto.getStatus());

        return mapToDTO(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
