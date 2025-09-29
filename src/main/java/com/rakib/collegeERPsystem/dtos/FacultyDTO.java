package com.rakib.collegeERPsystem.dtos;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacultyDTO {

    private Long id;             // inherited from BaseEntity
    private String name;
    private String email;
    private String phone;
    private String designation;
    private Long userId;         // reference to User
    private String userName;     // optional, if you want to include User's name
    private Long departmentId;   // reference to Department
    private String departmentName; // optional, Department name
}
