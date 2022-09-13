package com.kopo.spring.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponse> handleUserException(final UserException exception) {
        log.warn("UserException occur : ", exception);
        return this.makeErrorResponseEntity(exception.getExceptionResult());

    }

    private ResponseEntity<ErrorResponse> makeErrorResponseEntity(final UserExceptionResult exceptionResult) {
        return ResponseEntity.status(exceptionResult.getStatus())
                .body(new ErrorResponse(exceptionResult.name(), exceptionResult.getMessage()));
    }
}
