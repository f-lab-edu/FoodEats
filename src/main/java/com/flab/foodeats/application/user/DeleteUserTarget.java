package com.flab.foodeats.application.user;

import com.flab.foodeats.common.Encryption;

public class DeleteUserTarget {

	private final String userId;
	private final String password;

	public DeleteUserTarget(String userId, String password) {
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