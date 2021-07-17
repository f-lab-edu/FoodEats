package com.flab.foodeats.model;

import javax.validation.constraints.NotEmpty;

public class UpdateFormDTO {

	/**
	 * param id : 사용자가 회원가입 시 입력할 id
	 * param password : 사용자가 회원가입 시 입력할 password
	 * param name : 사용자가 회원가입 시 입력할 nickname
	 */

	@NotEmpty
	private String id;
	@NotEmpty
	private String password;
	@NotEmpty
	private String name;

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public UpdateFormDTO() {
	}

	public UpdateFormDTO(String id, String password, String name) {
		this.id = id;
		this.password = password;
		this.name = name;
	}
}
