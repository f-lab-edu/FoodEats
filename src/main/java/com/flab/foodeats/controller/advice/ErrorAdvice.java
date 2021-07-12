package com.flab.foodeats.controller.advice;


import com.flab.foodeats.model.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorAdvice {

    /**
     * IllegalArgument
     * 파라미터 오류
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResult> illegalExHandler(IllegalArgumentException e) {
        ErrorResult errorResult = new ErrorResult("IllegalArgument",e.getMessage());
        return new ResponseEntity<>(errorResult,HttpStatus.BAD_REQUEST);
    }

    /**
     * DuplicateKey
     * 중복 오류
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResult> runtimeException(DuplicateKeyException e) {
        ErrorResult errorResult = new ErrorResult("DuplicateKey",e.getMessage());
        return new ResponseEntity<>(errorResult,HttpStatus.BAD_REQUEST);
    }

    /**
     * DuplicateKey
     * 중복 오류
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResult> nullPointerHandler(NullPointerException e) {
        ErrorResult errorResult = new ErrorResult("NullPointer", e.getMessage());
        return new ResponseEntity<>(errorResult,HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception
     * 최상위 오류 / 무조건 작동
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResult> exceptionHandler (Exception e) {
        ErrorResult errorResult = new ErrorResult("Exception", e.getMessage());
        return new ResponseEntity<>(errorResult,HttpStatus.BAD_REQUEST);
    }


//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler
//    public ErrorResult exHandle(Exception e) {
//        log.error("[exceptionHandle] ex", e);
//        return new ErrorResult("EX", "내부 오류");
//    }

}
