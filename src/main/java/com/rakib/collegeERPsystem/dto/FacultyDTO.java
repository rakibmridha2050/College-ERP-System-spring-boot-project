package com.rakib.collegeERPsystem.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacultyDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String designation;
    private Long userId;         // Make sure this is included
    private String userName;
    private Long departmentId;
    private String departmentName;
}