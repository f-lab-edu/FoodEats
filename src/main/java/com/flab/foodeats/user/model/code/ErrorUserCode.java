package com.flab.foodeats.user.model.code;

public enum ErrorUserCode {

	PASSWORD_NOT_MATCH("Password Not Match"),
	ID_NOT_EXIST("ID Not Exist"),
	ID_EXIST("ID Already Exist"),
	ID_NOT_MATCH("ID Not Match"),
	SESSION_NO_AUTHORIZED("Session No Authorized"),
	AUTH_NO_ANNOTATION("Interceptor No Annotation"),
	SESSION_CAST_UNMATCH("Session Cast UnMatch"),
	SESSION_INVALID_AUTHENTICATION("Session Invalid At Authentication");

	private final String message;

	ErrorUserCode(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
