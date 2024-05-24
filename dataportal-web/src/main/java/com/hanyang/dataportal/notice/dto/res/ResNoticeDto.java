package com.hanyang.dataportal.notice.dto.res;

import com.hanyang.dataportal.notice.domain.Notice;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ResNoticeDto {

    private Long noticeId;

    private String label;

    private String title;
    private String content;
    private LocalDate createDate;
    private LocalDate updateTime;
    private Integer view;
    private String adminName;

    public ResNoticeDto(Notice notice) {
        this.noticeId = notice.getNoticeId();
        this.label = notice.getLabel();
        this.title = notice.getTitle();
        this.content = notice.getContent();
        this.createDate = notice.getCreateDate();
        this.updateTime = notice.getUpdateDate();
        this.view = notice.getView();
        this.adminName = notice.getAdmin().getName();
    }
}
