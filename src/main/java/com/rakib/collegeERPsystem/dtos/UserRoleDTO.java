package com.rakib.collegeERPsystem.dtos;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRoleDTO {

    private Long userRoleId;
    private Long userId;
    private String username;
    private Long roleId;
    private String roleName;
    private String assignedDate;
}
