package com.rakib.collegeERPsystem.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long userId;
    private String username;
    private String email;
    private String roleType;

}
