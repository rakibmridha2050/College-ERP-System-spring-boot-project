package com.rakib.collegeERPsystem.service;

import com.rakib.collegeERPsystem.dto.NoticeDTO;
import com.rakib.collegeERPsystem.entity.Notice;
import com.rakib.collegeERPsystem.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public List<Notice> getAllPublishedNotices() {
        return noticeRepository.findByIsPublishedTrue();
    }

    public Notice getById(Long id){
        return noticeRepository.findById(id).orElseThrow();
    }


    public Notice createNotice(Notice notice) {
        notice.setPublishDate(notice.getPublishDate() == null ?
                java.time.LocalDateTime.now() : notice.getPublishDate());
        return noticeRepository.save(notice);
    }

    public void deleteNotice(Long id) {
        noticeRepository.deleteById(id);
    }
}
