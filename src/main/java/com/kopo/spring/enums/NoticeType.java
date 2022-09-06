package com.kopo.spring.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NoticeType {
    Y("Y"),
    N("N"),
    ;

    private final String noticeType;
}
