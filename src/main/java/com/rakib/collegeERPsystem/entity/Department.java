package com.rakib.collegeERPsystem.entity;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department extends BaseEntity{


    @Column(nullable = false, unique = true)
    private String deptName;

    @Column(nullable = false, unique = true)
    private String deptCode;

    // Many departments can have one HOD (faculty)
//    @ManyToOne
//    @JoinColumn(name = "hod_id", referencedColumnName = "facultyId")
//    private Faculty hod;
}
