package com.rakib.collegeERPsystem.dto;

import com.rakib.collegeERPsystem.enums.NoticeType;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeDTO {
    private Long noticeId;
    private String title;
    private String content;
    private NoticeType noticeType;
    private Boolean isPublished;
    private LocalDateTime publishDate;
    private LocalDateTime expiryDate;
    private Long departmentId;
    private Long classId;
    private Long sectionId;
    private Long courseId;
    private Long postedById;
}
