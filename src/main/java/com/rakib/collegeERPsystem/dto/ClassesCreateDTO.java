package com.rakib.collegeERPsystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class ClassesCreateDTO {
    private String className;
    private Long departmentId;
    private List<Long> sectionIds; // Optional: if you want to assign sections during creation
}
