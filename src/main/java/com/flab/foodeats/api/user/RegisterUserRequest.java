package com.flab.foodeats.api.user;

import javax.validation.constraints.NotBlank;

import com.flab.foodeats.application.user.RegisterUserTarget;

public class RegisterUserRequest {

	@NotBlank(message = "Input Your userId")
	private String userId;
	@NotBlank(message = "Input Your password")
	private String password;
	@NotBlank(message = "Input Your name")
	private String name;

	public RegisterUserRequest() {
	}

	public RegisterUserRequest(String userId, String password, String name) {

		this.userId = userId;
		this.password = password;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RegisterUserTarget toParam() {
		return new RegisterUserTarget(userId, password, name);
	}

}