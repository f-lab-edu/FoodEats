package com.flab.foodeats.user.model;

import javax.validation.constraints.NotBlank;

public class UpdateFormDTO {

	/**
	 * 회원수정
	 * param password : 비밀번호
	 * param name : 이름
	 */

	@NotBlank(message = "Input Your Password")
	private String password;
	@NotBlank(message = "Input Your Message")
	private String name;

	public UpdateFormDTO() {
	}

	public UpdateFormDTO(String password, String name) {
		this.password = password;
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

}
