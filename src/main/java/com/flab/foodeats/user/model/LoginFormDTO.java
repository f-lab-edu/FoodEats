package com.flab.foodeats.user.model;

import javax.validation.constraints.NotBlank;

public class LoginFormDTO {

	/**
	 * 로그인
	 * param id : 아이디
	 * param password : 비밀번호
	 */

	@NotBlank(message = "Input Your ID")
	private String id;
	@NotBlank(message = "Input Your Password")
	private String password;

	public LoginFormDTO() {
	}

	public LoginFormDTO(String id, String password) {
		this.id = id;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}
}

