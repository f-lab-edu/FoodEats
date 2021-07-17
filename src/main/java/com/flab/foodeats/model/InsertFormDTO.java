package com.flab.foodeats.model;

import javax.validation.constraints.NotEmpty;

public class InsertFormDTO {

	/**
	 * param id : 회원가입 시 입력할 사용자 id
	 * param password : 회원가입 시 입력할 사용자 password
	 * param name : 회원가입 시 입력할 사용자 닉네임
	 */

	@NotEmpty
	private String id;
	@NotEmpty
	private String password;
	@NotEmpty
	private String name;

	public InsertFormDTO() {
	}

	public InsertFormDTO(String id, String password, String name) {
		this.id = id;
		this.password = password;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}
}
