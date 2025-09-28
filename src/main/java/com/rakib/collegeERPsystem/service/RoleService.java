package com.rakib.collegeERPsystem.service;


import com.rakib.collegeERPsystem.dtos.RoleDTO;
import com.rakib.collegeERPsystem.entity.Role;
import com.rakib.collegeERPsystem.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // Create Role
    public RoleDTO createRole(Role role) {
        if (roleRepository.existsByRoleName(role.getRoleName())) {
            throw new RuntimeException("Role already exists!");
        }
        Role savedRole = roleRepository.save(role);
        return mapToDTO(savedRole);
    }

    // Get All Roles
    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Get Role by ID
    public RoleDTO getRoleById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        return mapToDTO(role);
    }

    // Update Role
    public RoleDTO updateRole(Long id, Role updatedRole) {
        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        existingRole.setRoleName(updatedRole.getRoleName());
        Role savedRole = roleRepository.save(existingRole);

        return mapToDTO(savedRole);
    }

    // Delete Role
    public void deleteRole(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new RuntimeException("Role not found!");
        }
        roleRepository.deleteById(id);
    }

    // Mapping
    private RoleDTO mapToDTO(Role role) {
        return RoleDTO.builder()
                .roleId(role.getRoleId())
                .roleName(role.getRoleName())
                .build();
    }
}
