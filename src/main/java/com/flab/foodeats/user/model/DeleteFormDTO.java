package com.flab.foodeats.user.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DeleteFormDTO {

	@NotNull
	private String id;
	@NotEmpty
	private String password;

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public DeleteFormDTO() {
	}

	public DeleteFormDTO(String id, String password) {
		this.id = id;
		this.password = password;
	}
}
