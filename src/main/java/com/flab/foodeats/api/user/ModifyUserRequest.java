package com.flab.foodeats.api.user;

import javax.validation.constraints.NotBlank;

import com.flab.foodeats.application.user.ModifyUserTarget;

public class ModifyUserRequest {

	@NotBlank(message = "Input Your password")
	private String password;
	@NotBlank(message = "Input Your name")
	private String name;

	public ModifyUserRequest() {
	}

	public ModifyUserRequest(String password, String name) {
		this.password = password;
		this.name = name;
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

	public ModifyUserTarget toParam(String userId) {
		return new ModifyUserTarget(userId, password, name);
	}
}