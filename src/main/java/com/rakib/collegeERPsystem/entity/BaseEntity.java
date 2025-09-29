package com.rakib.collegeERPsystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    //    private LocalDateTime deleteAt;
    private boolean active;



    private String createdBy;
    private String updatedBy;
//    private String deletedBy;



    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.active = true;


    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = LocalDateTime.now();

    }
//
//    public void softDelete(){
//        this.deleteAt = LocalDateTime.now();
//    }
//
//    public boolean isDelete(){
//        return this.deleteAt != null;
//    }
}
