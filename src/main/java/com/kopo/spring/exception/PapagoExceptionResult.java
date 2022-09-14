package com.kopo.spring.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PapagoExceptionResult {
    BAD_LANGUAGE_TYPE(HttpStatus.BAD_REQUEST, "한국어와 영어만 번역됩니다"),
    ;

    private final HttpStatus status;
    private final String message;
}
