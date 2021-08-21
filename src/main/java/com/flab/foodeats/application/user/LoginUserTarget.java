package com.flab.foodeats.application.user;

import com.flab.foodeats.common.Encryption;

public class LoginUserTarget {

	private final String userId;
	private final String password;

	public LoginUserTarget(String userId, String password) {
		this.userId = userId;
		this.password = Encryption.encoder(password);
	}

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

}