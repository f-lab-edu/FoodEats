package com.flab.foodeats.application.user;

import com.flab.foodeats.common.Encryption;
import com.flab.foodeats.domain.user.User;

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

	public User toEntity() {
		return new User(userId, password);
	}
}