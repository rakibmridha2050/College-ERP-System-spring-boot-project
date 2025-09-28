package com.rakib.collegeERPsystem.repository;


import com.rakib.collegeERPsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);
    boolean existsByEmail(String email);

}
