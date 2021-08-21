package com.flab.foodeats.common.response;

public enum SuccessUserCode {

	USER_REGISTER_SUCCESS("Register Success"),
	USER_INSERT_SUCCESS("Insert Success"),
	USER_LOGIN_SUCCESS("Login Success"),
	USER_LOGOUT_SUCCESS("Logout Success"),
	USER_FINDALL_SUCCESS("Find_ALL_Member Success"),
	USER_FINDMEMBER_SUCCESS("Find_Member Success"),
	USER_UPDATE_SUCCESS("Update Success"),
	USER_DELETE_SUCCESS("Delete Success");

	private final String message;

	SuccessUserCode(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
