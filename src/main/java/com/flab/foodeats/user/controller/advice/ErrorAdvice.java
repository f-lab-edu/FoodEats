package com.flab.foodeats.user.controller.advice;

import com.flab.foodeats.global.ApiResponse;
import com.flab.foodeats.global.StatusCode;

import lombok.extern.slf4j.Slf4j;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	public ResponseEntity<ApiResponse> illegalExHandler(IllegalArgumentException e) {
		ApiResponse apiResponse = new ApiResponse(StatusCode.FAIL, e.getMessage());
		return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * DuplicateKey
	 * 중복 오류
	 */
	@ExceptionHandler
	public ResponseEntity<ApiResponse> runtimeException(DuplicateKeyException e) {
		ApiResponse apiResponse = new ApiResponse(StatusCode.FAIL, e.getMessage());
		return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * NullPointer
	 * 널 포인트 오류
	 */
	@ExceptionHandler
	public ResponseEntity<ApiResponse> nullPointerHandler(NullPointerException e) {
		ApiResponse apiResponse = new ApiResponse(StatusCode.FAIL, e.getMessage());
		return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * MethodArgmentNotValid
	 * NotBlank
	 * 입력 파라미터 부재 오류
	 */
	@ExceptionHandler
	public ResponseEntity<ApiResponse> MethodArgumentNotValidHandler(MethodArgumentNotValidException e) {
		String errors = null;
		for(ObjectError error : e.getBindingResult().getAllErrors()) {
			errors=error.getDefaultMessage();
		}

		ApiResponse apiResponse = new ApiResponse(StatusCode.FAIL, errors);
		return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Exception
	 * 최상위 오류
	 */
	@ExceptionHandler
	public ResponseEntity<ApiResponse> exceptionHandler(Exception e) {
		ApiResponse apiResponse = new ApiResponse(StatusCode.FAIL, e.getMessage());
		return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
	}
}
