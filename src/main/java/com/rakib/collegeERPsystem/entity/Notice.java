package com.rakib.collegeERPsystem.entity;

import com.rakib.collegeERPsystem.enums.NoticeType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notice extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime publishDate = LocalDateTime.now();

    @Column(nullable = false)
    private Boolean isPublished = false;

    // 🔗 Relation with Department (optional)
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    // 🔗 Relation with Class (if notice is for a specific class)
    @ManyToOne
    @JoinColumn(name = "class_id")
    private Classes classEntity;

    // 🔗 Relation with Section (optional)
    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    // 🔗 Relation with Course (optional)
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    // 🔗 Posted by Employee/Teacher
    @ManyToOne
    @JoinColumn(name = "posted_by")
    private Teacher postedBy;

    // 📌 Optional: Type of Notice (e.g., Exam, Event, Holiday)
    @Enumerated(EnumType.STRING)
    @Column(name = "notice_type")
    private NoticeType noticeType;

    // 📌 Optional: Expiration date
    private LocalDateTime expiryDate;
}
