package com.rakib.collegeERPsystem.dto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentDTO {

    private Long id;           // from BaseEntity
    private String rollNo;
    private String name;
    private String email;
    private String phone;
    private LocalDate dob;
    private String gender;
//    private Long userId;       // just the ID, not full User
//    private Long deptId;       // just the ID, not full Department
//    private String admissionYear;
    private boolean active;    // from BaseEntity



    private String studentId;
    private String program;
    private String department;
    private int currentSemester;
    private String permanentAddress;
    private String presentAddress;
    private Boolean isActive;
}
