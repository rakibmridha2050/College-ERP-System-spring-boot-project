package com.rakib.collegeERPsystem.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "classes")
@Data
@NoArgsConstructor
public class Classes extends BaseEntity{

    private String className; // e.g. "BSc in CSE - Year 2"

    @ManyToOne
    private Department department;

    @OneToMany(mappedBy = "classes")
    private List<Section> sections;
}
