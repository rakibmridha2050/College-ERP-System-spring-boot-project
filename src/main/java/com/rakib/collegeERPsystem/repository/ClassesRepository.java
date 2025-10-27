package com.rakib.collegeERPsystem.repository;

import com.rakib.collegeERPsystem.entity.Classes;
import com.rakib.collegeERPsystem.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassesRepository extends JpaRepository<Classes, Long> {

    Classes findByClassName(String className);

    List<Classes> findByDepartment(Department department);
    List<Classes> findByDepartmentId(Long departmentId);
    List<Classes> findByActiveTrue();
    boolean existsByClassName(String className);

    Optional<Classes> findByIdAndActiveTrue(Long id);

    List<Classes> findByDepartment_Id(Long departmentId);


    Classes findByClassNameAndDepartment(String className, Department department);
}