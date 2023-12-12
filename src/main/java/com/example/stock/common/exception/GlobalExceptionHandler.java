package com.example.stock.common.exception;

import com.example.stock.domain.common.CommonRes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<CommonRes> handleNoSuchElementFoundException(NoSuchElementException e) {
        return ResponseEntity.ok(CommonRes.failRes(e.getMessage()));
    }

}
