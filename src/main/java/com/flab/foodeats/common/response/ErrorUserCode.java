package com.flab.foodeats.common.response;

public enum ErrorUserCode {

	PASSWORD_NOT_MATCH("Password Not Match"),
	ID_NOT_EXIST("ID Not Exist"),
	ID_EXIST("ID Already Exist"),
	ID_NOT_MATCH("ID Not Match"),
	SESSION_NO_AUTHORIZED("Session No Authorized"),
	AUTH_ANNOTATION_REQUIRED("Interceptor No Annotation"),
	AUTH_ROLE_NOT_MATCH("Auth Role Not Mathch"),
	SESSION_CAST_UNMATCH("Session Cast UnMatch"),
	SESSION_INVALID_AUTHENTICATION("Session Invalid At Authentication"),
	SHOP_EXIST("Shop Already Exist"),
	ESSENTIAL_INFO_NOT_EXIST("Essential Info Is Not Exist");

	private final String message;

	ErrorUserCode(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}