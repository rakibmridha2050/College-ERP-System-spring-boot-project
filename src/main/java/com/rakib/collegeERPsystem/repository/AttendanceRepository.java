package com.rakib.collegeERPsystem.repository;

import com.rakib.collegeERPsystem.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findBySectionIdAndAttendanceDate(Long sectionId, LocalDate attendanceDate);

}
