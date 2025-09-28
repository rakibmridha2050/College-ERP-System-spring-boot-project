package com.rakib.collegeERPsystem.repository;

import org.springframework.stereotype.Repository;
import com.rakib.collegeERPsystem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

    Optional<Role> findByRoleName(String roleName);
    boolean existsByRoleName(String roleName);
}
