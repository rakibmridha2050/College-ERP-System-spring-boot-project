package com.rakib.collegeERPsystem.entity;

import jakarta.persistence.*;
import lombok.*;

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


    // Many departments can have one HOD (faculty)
//    @ManyToOne
//    @JoinColumn(name = "hod_id", referencedColumnName = "facultyId")
//    private Faculty hod;
}
