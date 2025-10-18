package com.rakib.collegeERPsystem.repository;

import com.rakib.collegeERPsystem.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    // Enrollment এর উপর ভিত্তি করে attendance খুঁজতে
    List<Attendance> findByEnrollment_EnrollmentId(Long enrollmentId);

    // Faculty এর উপর ভিত্তি করে attendance খুঁজতে
    List<Attendance> findByFaculty_Id(Long facultyId);

    // Class এর উপর ভিত্তি করে attendance খুঁজতে (optional)
    List<Attendance> findByClasses_Id(Long classId);

    // Section এর উপর ভিত্তি করে attendance খুঁজতে (optional)
    List<Attendance> findBySection_Id(Long sectionId);

}
