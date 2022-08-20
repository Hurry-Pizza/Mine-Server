package com.hurrypizza.digda.api.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("handleMethodArgumentNotValidException", e);
        return ResponseEntity.badRequest().body(ExceptionResponse.of(e.getMessage()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<ExceptionResponse> missingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("MissingServletRequestParameterException", e);
        return ResponseEntity.badRequest().body(ExceptionResponse.of(e.getMessage()));
    }

    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ExceptionResponse> handleBindException(BindException e) {
        log.error("handleBindException", e);
        return ResponseEntity.badRequest().body(ExceptionResponse.of(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ExceptionResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("handleMethodArgumentTypeMismatchException", e);
        return ResponseEntity.badRequest().body(ExceptionResponse.of(e.getMessage()));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ExceptionResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException", e);
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(ExceptionResponse.of(e.getMessage()));
    }

//    @ExceptionHandler(AccessDeniedException.class)
//    protected ResponseEntity<ExceptionResponse> handleAccessDeniedException(AccessDeniedException e) {
//        log.error("handleAccessDeniedException", e);
//        final var response = ExceptionResponse.of(ExceptionCode.HANDLE_ACCESS_DENIED);
//        return new ResponseEntity<>(response, ExceptionCode.HANDLE_ACCESS_DENIED.getStatus());
//    }
//
//    @ExceptionHandler(AuthenticationException.class)
//    protected ResponseEntity<ExceptionResponse> handleAuthenticationException(AuthenticationException e) {
//        log.error("AuthenticationException", e);
//        final var response = ExceptionResponse.of(ExceptionCode.AUTHENTICATION_FAILURE);
//        return new ResponseEntity<>(response, ExceptionCode.AUTHENTICATION_FAILURE.getStatus());
//    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ExceptionResponse> handleException(Exception e) {
        log.error("Exception (Unexpected) ", e);
        return ResponseEntity.internalServerError()
                       .body(ExceptionResponse.of(e.getMessage()));
    }

}