package com.rakib.collegeERPsystem.repository;

import com.rakib.collegeERPsystem.entity.Notice;
import com.rakib.collegeERPsystem.enums.NoticeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

    List<Notice> findByIsPublishedTrue();

    List<Notice> findByDepartment_Id(Long departmentId);

    List<Notice> findByClassEntity_Id(Long classId);

    List<Notice> findByNoticeType(NoticeType noticeType);
}
