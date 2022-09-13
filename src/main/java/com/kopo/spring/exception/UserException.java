package com.kopo.spring.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserException extends RuntimeException {



    private final UserExceptionResult exceptionResult;
}
