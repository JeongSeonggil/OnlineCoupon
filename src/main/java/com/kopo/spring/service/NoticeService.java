package com.kopo.spring.service;

import com.kopo.spring.dto.NoticeDto;
import com.kopo.spring.repository.entity.NoticeEntity;

import java.util.List;

public interface NoticeService {
    NoticeDto saveNotice(NoticeDto noticeDto) throws Exception;

    List<NoticeDto> getNoticeList() throws Exception;
    NoticeDto getOneNotice(final long noticeSeq) throws Exception;

    NoticeDto modifyNotice(NoticeDto noticeDto) throws Exception;

    String deleteNotice(Long noticeSeq, final String userId) throws Exception;
}
