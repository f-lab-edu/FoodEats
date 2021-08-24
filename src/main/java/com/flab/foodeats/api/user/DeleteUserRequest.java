package com.flab.foodeats.api.user;

import javax.validation.constraints.NotBlank;

import com.flab.foodeats.application.user.DeleteUserTarget;

public class DeleteUserRequest {

	@NotBlank(message = "Input Your password")
	private String password;


	public DeleteUserRequest(){

	}

	public DeleteUserRequest(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public DeleteUserTarget toParam(String userId) {
		return new DeleteUserTarget(userId, password);
	}

}
