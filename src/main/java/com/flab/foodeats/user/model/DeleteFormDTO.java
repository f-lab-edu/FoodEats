package com.flab.foodeats.user.model;

import javax.validation.constraints.NotBlank;

public class DeleteFormDTO {

	/**
	 * 회원삭제
	 * param id : 아이디
	 * param password : 비밀번호
	 */

	@NotBlank(message = "Input Your Id")
	private String id;
	@NotBlank(message = "Input Your Password")
	private String password;

	public DeleteFormDTO() {
	}

	public DeleteFormDTO(String id, String password) {
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
