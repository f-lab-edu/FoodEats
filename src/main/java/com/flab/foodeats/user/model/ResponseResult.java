package com.flab.foodeats.user.model;

public class ResponseResult {

	/**
	 * param status : 응답 상태 (Http body)
	 *  - 3가지 응답
	 *  - success, error, fail
	 *  - 참고 : https://github.com/omniti-labs/jsend
	 *
	 * param message : 응답 메시지 (Http body)
	 */

	private ResponseStatus status;
	private Object message;

	public ResponseResult(ResponseStatus status, Object message) {
		this.status = status;
		this.message = message;
	}

	public ResponseStatus getStatus() {
		return status;
	}

	public Object getMessage() {
		return message;
	}
}
