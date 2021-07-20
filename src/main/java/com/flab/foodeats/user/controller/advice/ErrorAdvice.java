package com.flab.foodeats.user.controller.advice;

import com.flab.foodeats.user.model.ResponseResult;
import com.flab.foodeats.user.model.ResponseStatus;

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
	public ResponseEntity<ResponseResult> illegalExHandler(IllegalArgumentException e) {
		ResponseResult responseResult = new ResponseResult(ResponseStatus.FAIL, e.getMessage());
		return new ResponseEntity<>(responseResult, HttpStatus.BAD_REQUEST);
	}

	/**
	 * DuplicateKey
	 * 중복 오류
	 */
	@ExceptionHandler
	public ResponseEntity<ResponseResult> runtimeException(DuplicateKeyException e) {
		ResponseResult responseResult = new ResponseResult(ResponseStatus.FAIL, e.getMessage());
		return new ResponseEntity<>(responseResult, HttpStatus.BAD_REQUEST);
	}

	/**
	 * DuplicateKey
	 * 중복 오류
	 */
	@ExceptionHandler
	public ResponseEntity<ResponseResult> nullPointerHandler(NullPointerException e) {
		ResponseResult responseResult = new ResponseResult(ResponseStatus.FAIL, e.getMessage());
		return new ResponseEntity<>(responseResult, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Exception
	 * 최상위 오류 / 무조건 작동
	 */
	@ExceptionHandler
	public ResponseEntity<ResponseResult> exceptionHandler(Exception e) {
		ResponseResult responseResult = new ResponseResult(ResponseStatus.FAIL, e.getMessage());
		return new ResponseEntity<>(responseResult, HttpStatus.BAD_REQUEST);
	}
}
