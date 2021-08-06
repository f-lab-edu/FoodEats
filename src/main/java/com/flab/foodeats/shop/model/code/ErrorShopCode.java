package com.flab.foodeats.shop.model.code;

public enum ErrorShopCode {

	SHOP_EXIST("Shop Already Exist")
	;

	private final String message;

	ErrorShopCode(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
