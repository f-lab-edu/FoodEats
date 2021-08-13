package com.flab.foodeats.search.model.code;

public enum SearchErrorCode {
	NOT_FOUND_SEARCH_RESULT("Not found search result");

	private final String message;

	SearchErrorCode(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
