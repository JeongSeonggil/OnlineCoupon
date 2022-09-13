package com.kopo.spring.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserExceptionResult {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다"),
    USER_EMAIL_ERROR(HttpStatus.BAD_REQUEST, "중복된 이메일 입니다"),
    USER_ID_ERROR(HttpStatus.BAD_REQUEST, "중복된 아이디 입니다"),
    USER_PASSWORD_NOT_MATCHED(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다")
    ;

    private final HttpStatus status;
    private final String message;
}
