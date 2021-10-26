package com.flab.foodeats.common.response;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T,M> {

	private StatusCode status;
	private T message;
	private M data;

	public ApiResponse(StatusCode status, T message) {
		this.status = status;
		this.message = message;
	}

	public ApiResponse(StatusCode status, T message, M data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public StatusCode getStatus() {
		return status;
	}

	public void setStatus(StatusCode status) {
		this.status = status;
	}

	public T getMessage() {
		return message;
	}

	public void setMessage(T message) {
		this.message = message;
	}

	public M getData() {
		return data;
	}

	public void setData(M data) {
		this.data = data;
	}

	public static ApiResponse responseList(StatusCode status, String message, List data) {
		return new ApiResponse(status, message, data);
	}

	public static ApiResponse responseError(StatusCode status, Map message) {
		return new ApiResponse(status, message);
	}

	public static ApiResponse responseMessage(StatusCode status, String message) {
		return new ApiResponse(status, message);
	}

	// public static ApiResponse responseData(StatusCode status, String message, String data) {
	// 	return new ApiResponse(status, message, data);
	// }
	public static ApiResponse responseData(StatusCode status, String message, Object data) {
		return new ApiResponse(status, message, data);
	}

}
