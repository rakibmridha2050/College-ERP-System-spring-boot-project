package com.rakib.collegeERPsystem.service;

import com.rakib.collegeERPsystem.dtos.UserRoleDTO;
import com.rakib.collegeERPsystem.entity.Role;
import com.rakib.collegeERPsystem.entity.User;
import com.rakib.collegeERPsystem.entity.UserRole;
import com.rakib.collegeERPsystem.repository.RoleRepository;
import com.rakib.collegeERPsystem.repository.UserRepository;
import com.rakib.collegeERPsystem.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository,
                           UserRepository userRepository,
                           RoleRepository roleRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    // Assign Role to User
    public UserRoleDTO assignRoleToUser(Long userId, Long roleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        UserRole userRole = UserRole.builder()
                .user(user)
                .role(role)
                .build();

        UserRole saved = userRoleRepository.save(userRole);
        return mapToDTO(saved);
    }

    // Get Roles by User
    public List<UserRoleDTO> getRolesByUser(Long userId) {
        return userRoleRepository.findByUserUserId(userId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<UserRoleDTO> getUsersByRole(Long roleId) {
        return userRoleRepository.findByRoleRoleId(roleId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    public UserRoleDTO updateUserRole(Long userRoleId, Long newRoleId) {
        UserRole existingUserRole = userRoleRepository.findById(userRoleId)
                .orElseThrow(() -> new RuntimeException("UserRole not found"));

        Role newRole = roleRepository.findById(newRoleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        existingUserRole.setRole(newRole);
        UserRole saved = userRoleRepository.save(existingUserRole);

        return mapToDTO(saved);
    }


    // Remove Role from User
    public void removeRole(Long userRoleId) {
        if (!userRoleRepository.existsById(userRoleId)) {
            throw new RuntimeException("UserRole not found");
        }
        userRoleRepository.deleteById(userRoleId);
    }

    // Mapping
    private UserRoleDTO mapToDTO(UserRole userRole) {
        return UserRoleDTO.builder()
                .userRoleId(userRole.getUserRoleId())
                .userId(userRole.getUser().getUserId())
                .username(userRole.getUser().getUsername())
                .roleId(userRole.getRole().getRoleId())
                .roleName(userRole.getRole().getRoleName())
                .assignedDate(userRole.getAssignedDate().toString())
                .build();
    }
}
