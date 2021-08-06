package com.flab.foodeats.global;

public class ApiResponse {

	/**
	 * User Api - Json 응답
	 * param status : ResponseStatus (Success, fail, error)
	 * param message : 응답 데이터
	 */

	private StatusCode status;
	private Object message;

	public ApiResponse(StatusCode status, Object message) {
		this.status = status;
		this.message = message;
	}

	public StatusCode getStatus() {
		return status;
	}

	public Object getMessage() {
		return message;
	}

	public void setStatus(StatusCode status) {
		this.status = status;
	}

	public void setMessage(Object message) {
		this.message = message;
	}
}
