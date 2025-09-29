package com.rakib.collegeERPsystem.repository;


import com.rakib.collegeERPsystem.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long>{
    // Find UserRoles by user id
    List<UserRole> findByUserId(Long userId);

    // Find UserRoles by role id
    List<UserRole> findByRoleId(Long roleId);
}
