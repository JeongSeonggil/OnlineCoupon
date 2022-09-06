package com.kopo.spring.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kopo.spring.dto.NoticeDto;
import com.kopo.spring.repository.NoticeRepository;
import com.kopo.spring.repository.entity.NoticeEntity;
import com.kopo.spring.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

import javax.persistence.LockModeType;
import javax.persistence.Version;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;


    @Override
    @Transactional(rollbackOn = Exception.class)
    public NoticeDto saveNotice(final NoticeDto noticeDto) throws Exception {


        return new ObjectMapper().convertValue(noticeRepository.save(NoticeEntity.builder()
                .noticeYN(noticeDto.getNoticeYN())
                .title(noticeDto.getTitle())
                .contents(noticeDto.getContents())
                .readCnt(0L)
                .userId(noticeDto.getUserId())
                .regId(noticeDto.getUserId())
                .regDt(new Date().toString())
                .chgId(noticeDto.getUserId())
                .chgDt(new Date().toString()).build()), NoticeDto.class);
    }


    @Override
    @Transactional(rollbackOn = Exception.class)
    public NoticeDto getOneNotice(long noticeSeq) throws Exception {
        log.debug("noticeSeq : " + noticeSeq);

        noticeRepository.updateReadCnt(noticeSeq);


        return new ObjectMapper().convertValue(
                noticeRepository.findById(noticeSeq).orElseThrow(() -> new RuntimeException()), NoticeDto.class);
    }

    @Override
    public List<NoticeDto> getNoticeList() throws Exception {
        return noticeDtoListToNoticeEntityList(noticeRepository.findAll());
    }

    private List<NoticeDto> noticeDtoListToNoticeEntityList(final List<NoticeEntity> noticeEntities) throws Exception {
        return noticeEntities.stream()
                .map(noticeEntity -> new ObjectMapper().convertValue(noticeEntity, NoticeDto.class))
                .collect(Collectors.toList());
    }


    @Override
    public NoticeDto modifyNotice(NoticeDto noticeDto) throws Exception {
        NoticeEntity modifyResult = noticeRepository.save(new ObjectMapper()
                .convertValue(noticeDto, NoticeEntity.class));
        return new ObjectMapper().convertValue(modifyResult, NoticeDto.class);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public String deleteNotice(Long noticeSeq, final String userId) throws Exception {
        NoticeEntity noticeEntity = noticeRepository.findById(noticeSeq)
                .orElseThrow(() -> new RuntimeException());

        log.info("userId : " + userId);

        log.info("userId in DB : " + noticeEntity.getUserId());


        if (! noticeEntity.getUserId().equals(userId)) {
            throw new RuntimeException();

        }

        noticeRepository.delete(noticeEntity);

        return "deleteNotice End : " + noticeSeq;
    }
}
