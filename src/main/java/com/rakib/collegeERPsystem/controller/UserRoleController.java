package com.rakib.collegeERPsystem.controller;

import com.rakib.collegeERPsystem.dtos.UserRoleDTO;
import com.rakib.collegeERPsystem.service.UserRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-roles")
public class UserRoleController {

    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    // Assign Role to User
    @PostMapping("/assign")
    public ResponseEntity<UserRoleDTO> assignRole(@RequestParam Long userId, @RequestParam Long roleId) {
        return ResponseEntity.ok(userRoleService.assignRoleToUser(userId, roleId));
    }

    // Get Roles by User
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserRoleDTO>> getRolesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userRoleService.getRolesByUser(userId));
    }

    // Get Users by Role
    @GetMapping("/role/{roleId}")
    public ResponseEntity<List<UserRoleDTO>> getUsersByRole(@PathVariable Long roleId) {
        return ResponseEntity.ok(userRoleService.getUsersByRole(roleId));
    }

    // Update Role Assignment
    @PutMapping("/{userRoleId}")
    public ResponseEntity<UserRoleDTO> updateUserRole(
            @PathVariable Long userRoleId,
            @RequestParam Long newRoleId) {
        return ResponseEntity.ok(userRoleService.updateUserRole(userRoleId, newRoleId));
    }



    // Remove Role from User
    @DeleteMapping("/{userRoleId}")
    public ResponseEntity<String> removeRole(@PathVariable Long userRoleId) {
        userRoleService.removeRole(userRoleId);
        return ResponseEntity.ok("Role removed from user successfully");
    }
}
