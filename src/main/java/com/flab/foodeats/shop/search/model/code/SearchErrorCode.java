package com.flab.foodeats.shop.search.model.code;

public enum SearchErrorCode {
	NO_SEARCH_RESULT("No search results found.");

	private final String message;

	SearchErrorCode(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
