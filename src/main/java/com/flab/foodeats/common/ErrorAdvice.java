package com.flab.foodeats.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.flab.foodeats.common.response.ApiResponse;
import com.flab.foodeats.common.response.StatusCode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ErrorAdvice {

	/**
	 * IllegalArgument
	 * 파라미터 오류
	 */
	@ExceptionHandler
	public ResponseEntity<ApiResponse> illegalExHandler(IllegalArgumentException e) {
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.FAIL, e.getMessage());
		return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * DuplicateKey
	 * 중복 오류
	 */
	@ExceptionHandler
	public ResponseEntity<ApiResponse> runtimeException(DuplicateKeyException e) {
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.FAIL, e.getMessage());
		return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * NullPointer
	 * 널 포인트 오류
	 */
	@ExceptionHandler
	public ResponseEntity<ApiResponse> nullPointerHandler(NullPointerException e) {
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.FAIL, e.getMessage());
		return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * MethodArgmentNotValid
	 * NotBlank
	 * 입력 파라미터 부재 오류
	 */
	@ExceptionHandler
	public ResponseEntity<ApiResponse> MethodArgumentNotValidHandler(MethodArgumentNotValidException e) {

		Map<String, String> errors = new HashMap<>();
		e.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError)error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		ApiResponse apiResponse = ApiResponse.responseError(StatusCode.FAIL, errors);
		return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Exception
	 * 최상위 오류
	 */
	@ExceptionHandler
	public ResponseEntity<ApiResponse> exceptionHandler(Exception e) {
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.FAIL, e.getMessage());
		return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
	}
}
