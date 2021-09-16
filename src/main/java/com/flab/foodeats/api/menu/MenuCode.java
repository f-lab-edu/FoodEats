package com.flab.foodeats.api.menu;

public enum MenuCode {

	MENU_REGISTER_SUCCESS("Register Success"),
	MENU_UPDATE_SUCCESS("Menu Update Success"),
	MENU_DELETE_SUCCESS("Menu Delete Success"),
	OPTION_REGISTER_SUCCESS("Option Register Success"),
	OPTION_UPDATE_SUCCESS("Option Update Success"),
	OPTION_DELETE_SUCCESS("Option Delete Success"),
	ALEADY_EXIST_MENU("Aleady Exist Menu");

	private final String message;

	MenuCode(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
