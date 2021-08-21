package com.flab.foodeats.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {

	private StatusCode status;
	private Object message;
	private Object data;

	public ApiResponse(StatusCode status, Object message) {
		this.status = status;
		this.message = message;
	}

	public ApiResponse(StatusCode status, Object message, Object data) {
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

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static ApiResponse responseMessage(StatusCode status, Object message) {
		return new ApiResponse(status, message);
	}

	public static ApiResponse responseData(StatusCode status, Object message, Object data) {
		return new ApiResponse(status, message, data);
	}

}
