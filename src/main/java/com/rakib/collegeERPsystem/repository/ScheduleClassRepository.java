package com.rakib.collegeERPsystem.repository;

import com.rakib.collegeERPsystem.entity.ScheduleClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleClassRepository extends JpaRepository<ScheduleClass, Long> {
}
