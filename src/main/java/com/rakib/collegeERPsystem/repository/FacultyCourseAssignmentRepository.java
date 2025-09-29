package com.rakib.collegeERPsystem.repository;
import com.rakib.collegeERPsystem.entity.FacultyCourseAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyCourseAssignmentRepository extends JpaRepository<FacultyCourseAssignment, Long> {

    // Get all assignments for a specific faculty
    List<FacultyCourseAssignment> findByFacultyId(Long facultyId);

    // Get all assignments for a specific course
    List<FacultyCourseAssignment> findByCourseId(Long courseId);



    // Get assignments for a faculty in a specific semester and academic year
    List<FacultyCourseAssignment> findByFacultyIdAndSemesterAndAcademicYear(Long facultyId, String semester, String academicYear);

    List<FacultyCourseAssignment> findByCourseIdAndSemesterAndAcademicYear(
            Long courseId, String semester, String academicYear
    );

}