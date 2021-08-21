package com.flab.foodeats.application.user;

import com.flab.foodeats.common.Encryption;

public class RegisterUserTarget {

	private final String userId;
	private final String password;
	private final String name;

	public RegisterUserTarget(String userId, String password, String name) {
		this.userId = userId;
		this.password = Encryption.encoder(password);
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}
}
