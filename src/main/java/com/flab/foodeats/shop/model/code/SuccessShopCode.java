package com.flab.foodeats.shop.model.code;

public enum SuccessShopCode {

	SHOP_REGISTER_SUCCESS("Register Shop Success"),
	SHOP_UPDATE_SUCCESS("Update Shop Success"),
	SHOP_DELETE_SUCCESS("Delete Shop Success"),
	SHOP_OPEN_SUCCESS("Open Shop Success"),
	SHOP_CLOSE_SUCCESS("Close Shop Success")
	;

	private final String message;

	SuccessShopCode(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
