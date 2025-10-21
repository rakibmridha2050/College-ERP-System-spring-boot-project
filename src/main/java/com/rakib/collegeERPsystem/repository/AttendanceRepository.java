package com.rakib.collegeERPsystem.repository;

import com.rakib.collegeERPsystem.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    //
    List<Attendance> findByEnrollment_EnrollmentId(Long enrollmentId);

    //
    List<Attendance> findByFaculty_Id(Long facultyId);

    //
    List<Attendance> findByClasses_Id(Long classId);

    //
    List<Attendance> findBySection_Id(Long sectionId);

}
