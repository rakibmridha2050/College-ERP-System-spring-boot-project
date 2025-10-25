package com.rakib.collegeERPsystem.dto;

import lombok.Data;

@Data
public class SectionDTO {
    private Long id;
    private String sectionName;
    private ClassInfoDTO classInfo;

    @Data
    public static class ClassInfoDTO {
        private Long id;
        private String className;
        private String departmentName;
    }
}