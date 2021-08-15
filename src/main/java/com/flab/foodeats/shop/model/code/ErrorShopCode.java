package com.flab.foodeats.shop.model.code;

public enum ErrorShopCode {

	SHOP_EXIST("Shop Already Exist"),
	ESSENTIAL_INFO_NOT_EXIST("Essential Info Is Not Exist")
	;

	private final String message;

	ErrorShopCode(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
