package com.rakib.collegeERPsystem.mapper;

import com.rakib.collegeERPsystem.dto.ClassesDTO;
import com.rakib.collegeERPsystem.dto.DepartmentDTO;
import com.rakib.collegeERPsystem.dto.SectionDTO;
import com.rakib.collegeERPsystem.entity.Classes;
import com.rakib.collegeERPsystem.entity.Department;
import com.rakib.collegeERPsystem.entity.Section;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClassesMapper {

    public ClassesDTO toDTO(Classes classes) {
        ClassesDTO dto = new ClassesDTO();
        dto.setId(classes.getId());
        dto.setClassName(classes.getClassName());

        if (classes.getDepartment() != null) {
            DepartmentDTO deptDTO = new DepartmentDTO();
            deptDTO.setDeptId(classes.getDepartment().getId());
            deptDTO.setDeptName(classes.getDepartment().getDeptName());
            deptDTO.setDeptCode(classes.getDepartment().getDeptCode());
            dto.setDepartment(deptDTO);
        }

        if (classes.getSections() != null) {
            List<SectionDTO> sections = classes.getSections().stream().map(this::toSectionDTO).collect(Collectors.toList());
            dto.setSections(sections);
        }

        return dto;
    }

    private SectionDTO toSectionDTO(Section section) {
        SectionDTO dto = new SectionDTO();
        dto.setId(section.getId());
        dto.setSectionName(section.getSectionName());
        return dto;
    }
}