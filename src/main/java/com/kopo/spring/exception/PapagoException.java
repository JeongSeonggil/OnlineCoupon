package com.kopo.spring.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PapagoException extends RuntimeException{

    private final PapagoExceptionResult exceptionResult;
}
