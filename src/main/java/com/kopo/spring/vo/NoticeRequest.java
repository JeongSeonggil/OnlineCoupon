package com.kopo.spring.vo;

import com.kopo.spring.enums.NoticeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.security.DenyAll;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeRequest {
    private Long noticeSeq;
    private String title;
    private NoticeType noticeYN;
    private String contents;
    private String userId;
    private Long readCnt;
    private String regId;
    private String regDt;
    private String chgId;
    private String chgDt;
}
