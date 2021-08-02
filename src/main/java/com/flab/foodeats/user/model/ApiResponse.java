package com.flab.foodeats.user.model;

import com.flab.foodeats.user.model.code.StatusUserCode;

public class ApiResponse {

	/**
	 * User Api - Json 응답
	 * param status : ResponseStatus (Success, fail, error)
	 * param message : 응답 데이터
	 */

	private StatusUserCode status;
	private Object message;

	public ApiResponse(StatusUserCode status, Object message) {
		this.status = status;
		this.message = message;
	}

	public StatusUserCode getStatus() {
		return status;
	}

	public Object getMessage() {
		return message;
	}

	public void setStatus(StatusUserCode status) {
		this.status = status;
	}

	public void setMessage(Object message) {
		this.message = message;
	}
}
