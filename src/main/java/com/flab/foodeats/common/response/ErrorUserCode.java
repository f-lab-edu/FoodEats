package com.flab.foodeats.common.response;

public enum ErrorUserCode {

	PASSWORD_NOT_MATCH("Password Not Match"),
	ID_NOT_EXIST("ID NOT EXIST"),
	ID_EXIST("ID ALREADY EXIST"),
	ID_NOT_MATCH("ID NOT MATCH"),
	SESSION_NO_AUTHORIZED("SESSION NO AUTH"),
	AUTH_ANNOTATION_REQUIRED("INTERCEPTOR NO ANNOTATION"),
	AUTH_ROLE_NOT_MATCH("AUTH ROLE NOT MATCH"),
	SESSION_CAST_UNMATCH("SESSION CAST UNMATCH"),
	SESSION_INVALID_AUTHENTICATION("SESSION INVALID AT AUTH"),
	SHOP_EXIST("SHOP ALREADY EXIST"),
	SHOP_INFO_NOT_EXIST("SHOP_INFO_NOT_EXIST"),
	ENTITY_INFO_NOT_EXIST("ENTITY_INFO_NOT_EXIST");

	private final String message;

	ErrorUserCode(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}