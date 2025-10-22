package com.rakib.collegeERPsystem.service;

import com.rakib.collegeERPsystem.dto.ScheduleClassDTO;
import java.util.List;

public interface ScheduleClassService {
    ScheduleClassDTO create(ScheduleClassDTO scheduleClassDTO);
    List<ScheduleClassDTO> getAll();
    ScheduleClassDTO getById(Long id);
    ScheduleClassDTO update(Long id, ScheduleClassDTO scheduleClassDTO);
    void delete(Long id);
}
