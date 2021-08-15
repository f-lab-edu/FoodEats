package com.flab.foodeats.menu.model.code;

public enum SuccessMenuCode {

	MENU_REGISTER_SUCCESS("Menu Register Success"),
	MENU_UPDATE_SUCCESS("Menu Update Success"),
	MENU_DELETE_SUCCESS("Menu DELETE Success"),
	OPTION_UPDATE_SUCCESS("Option Update Success"),
	OPTION_DELETE_SUCCESS("Option Delete Success");

	private final String message;

	SuccessMenuCode(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
