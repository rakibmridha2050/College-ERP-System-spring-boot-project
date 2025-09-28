package com.rakib.collegeERPsystem.repository;


import com.rakib.collegeERPsystem.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long>{
    List<UserRole> findByUserUserId(Long userId);
    List<UserRole> findByRoleRoleId(Long roleId);
}
