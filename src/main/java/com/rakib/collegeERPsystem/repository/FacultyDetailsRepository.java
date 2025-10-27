package com.rakib.collegeERPsystem.repository;

import com.rakib.collegeERPsystem.entity.FacultyDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacultyDetailsRepository extends JpaRepository<FacultyDetails, Long> {

    Optional<FacultyDetails> findByFacultyId(Long facultyId);

    Optional<FacultyDetails> findByEmployeeId(String employeeId);

    Optional<FacultyDetails> findByNationalId(String nationalId);

    List<FacultyDetails> findByActiveTrue();

    @Query("SELECT fd FROM FacultyDetails fd WHERE fd.faculty.department.id = :departmentId AND fd.active = true")
    List<FacultyDetails> findByDepartmentId(@Param("departmentId") Long departmentId);

//    @Query("SELECT fd FROM FacultyDetails fd WHERE fd.faculty.department.name = :departmentName AND fd.active = true")
//    List<FacultyDetails> findByDepartmentName(@Param("departmentName") String departmentName);

    boolean existsByEmployeeId(String employeeId);

    boolean existsByNationalId(String nationalId);

    boolean existsByFacultyId(Long facultyId);
}