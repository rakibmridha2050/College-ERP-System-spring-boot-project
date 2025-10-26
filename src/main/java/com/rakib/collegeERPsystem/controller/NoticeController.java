package com.rakib.collegeERPsystem.controller;

import com.rakib.collegeERPsystem.entity.Notice;
import com.rakib.collegeERPsystem.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notices")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class NoticeController {

    private final NoticeService noticeService;


    @GetMapping("/{id}")
    public Notice getNoticeById(@PathVariable Long id){

            return noticeService.getById(id);

    }

    @GetMapping("/published")
    public List<Notice> getPublishedNotices() {
        return noticeService.getAllPublishedNotices();
    }

    @PostMapping
    public Notice createNotice(@RequestBody Notice notice) {
        return noticeService.createNotice(notice);
    }

    @DeleteMapping("/{id}")
    public void deleteNotice(@PathVariable Long id) {
        noticeService.deleteNotice(id);
    }
}
