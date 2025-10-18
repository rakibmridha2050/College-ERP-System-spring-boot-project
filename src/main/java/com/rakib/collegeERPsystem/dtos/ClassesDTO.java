package com.rakib.collegeERPsystem.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ClassesDTO {
    private Long id;
    private String className;
    private DepartmentDTO department;
    private List<SectionDTO> sections;
}
