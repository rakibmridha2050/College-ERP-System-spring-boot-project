package com.rakib.collegeERPsystem.dtos;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRoleDTO {

    private Long id;
    private Long userId;
    private String username;     // extra field for user name
    private Long roleId;
    private String roleName;     // extra field for role name
    private String assignedDate; // stored as string in DTO
}
