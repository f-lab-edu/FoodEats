package com.flab.foodeats.api.user;

import com.flab.foodeats.application.user.LoginUserTarget;

public class LoginUserRequest {

	private String userId;
	private String password;

	public LoginUserRequest() {
	}

	public LoginUserRequest(String userId, String password) {
		this.userId = userId;
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginUserTarget toParam() {
		return new LoginUserTarget(userId, password);
	}
}

