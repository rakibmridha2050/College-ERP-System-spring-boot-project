package com.rakib.collegeERPsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department extends BaseEntity{


    @Column(nullable = false, unique = true)
    private String deptName;

    @Column(nullable = false, unique = true)
    private String deptCode;

    @OneToMany(mappedBy = "department")
    private List<Classes> classes;



    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Faculty> facultyList = new ArrayList<>();


    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Course> courses = new ArrayList<>();

}
