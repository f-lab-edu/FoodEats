package com.flab.foodeats.menu.model.code;

public enum SuccessMenuCode {

	MENU_REGISTER_SUCCESS("Menu Register Success");

	private final String message;

	SuccessMenuCode(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
