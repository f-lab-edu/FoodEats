package com.flab.foodeats.shop.search.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.flab.foodeats.global.ApiResponse;
import com.flab.foodeats.global.StatusCode;

@RestControllerAdvice("com.flab.foodeats.shop.search.controller")
public class ShopSearchErrorAdvice {

	@ExceptionHandler
	public ResponseEntity<ApiResponse> nullPointerExHandler(NullPointerException e) {
		ApiResponse apiResponse = new ApiResponse(StatusCode.FAIL, e.getMessage());
		return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
	}
}
