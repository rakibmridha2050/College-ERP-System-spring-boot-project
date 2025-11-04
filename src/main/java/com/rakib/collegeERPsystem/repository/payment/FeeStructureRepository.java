package com.rakib.collegeERPsystem.repository.payment;

import com.rakib.collegeERPsystem.entity.payment.FeeStructure;
import com.rakib.collegeERPsystem.enums.FeeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeeStructureRepository extends JpaRepository<FeeStructure, Long> {
    List<FeeStructure> findByStatus(FeeStatus status);
//    List<FeeStructure> findByProgramAndSemesterAndAcademicSession(
//            String program, int semester, String academicSession);
}
