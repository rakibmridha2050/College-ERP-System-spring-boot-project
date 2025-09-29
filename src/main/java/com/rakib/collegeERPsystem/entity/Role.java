package com.rakib.collegeERPsystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role extends BaseEntity{
    @Column(nullable = false, unique = true)
    private String roleName;

}
