package com.rakib.collegeERPsystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class ClassesDTO {
    private Long id;
    private String className;
    private DepartmentDTO department;
    private List<SectionDTO> sections;
}
