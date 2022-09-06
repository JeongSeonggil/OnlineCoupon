package com.kopo.spring.repository.entity;

import com.kopo.spring.enums.NoticeType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table(name = "NOTICE")
@Cacheable
public class NoticeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeSeq;

    @NotNull
    @Column(name = "title", length = 500, nullable = false)
    private String title;

    @NotNull
    @Column(name = "notice_yn", length = 1, nullable = false)
    @Enumerated(EnumType.STRING)
    private NoticeType noticeYN;

    @NotNull
    @Column(name = "contents", nullable = false)
    private String contents;


    @NotNull
    @Column(name = "userId", nullable = false)
    private String userId;

    @NotNull
    @Column(name = "read_cnt", nullable = false)
    private Long readCnt;

    @Column(name = "reg_id", updatable = false)
    private String regId;

    @Column(name = "reg_dt", updatable = false)
    private String regDt;

    @Column(name = "chg_Id", updatable = false)
    private String chgId;

    @Column(name = "chg_Dt", updatable = false)
    private String chgDt;


}

