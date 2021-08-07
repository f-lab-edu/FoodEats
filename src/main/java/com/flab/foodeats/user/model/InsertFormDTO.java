package com.flab.foodeats.user.model;

import javax.validation.constraints.NotBlank;

public class InsertFormDTO {

	/**
	 * 회원가입
	 * param id : 아이디
	 * param password : 비밀번호
	 * param name : 이름
	 */

	@NotBlank(message = "Input Your ID")
	private String id;
	@NotBlank(message = "Input Your Password")
	private String password;
	@NotBlank(message = "Input Your Name")
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

	public void setId(String id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}
}
