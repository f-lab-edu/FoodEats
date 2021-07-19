package com.flab.foodeats.model;

import javax.validation.constraints.NotEmpty;

public class LoginFormDTO {

	/**
	 * param id : 사용자 id
	 * param password : 사용자 비밀번호
	 */

	@NotEmpty
	private String id;
	@NotEmpty
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

