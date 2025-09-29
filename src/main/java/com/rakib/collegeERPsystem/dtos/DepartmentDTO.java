package com.rakib.collegeERPsystem.dtos;


import lombok.Data;

@Data
public class DepartmentDTO {

    private Long deptId;
    private String deptName;
    private String deptCode;
    private Long hodId;// only HOD(Head of Department) faculty id
}
