package com.rakib.collegeERPsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "teachers")
@Data
@NoArgsConstructor
public class Teacher extends BaseEntity{



    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private String phoneNumber;
    private String gender;
    private LocalDate dob;

    private String designation;

    private String address;

}
