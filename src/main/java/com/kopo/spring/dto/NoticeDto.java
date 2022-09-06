package com.kopo.spring.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kopo.spring.enums.NoticeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDto extends RepresentationModel<NoticeDto> {
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
