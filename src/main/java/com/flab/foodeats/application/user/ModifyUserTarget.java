package com.flab.foodeats.application.user;

import com.flab.foodeats.common.Encryption;
import com.flab.foodeats.domain.user.User;

public class ModifyUserTarget {

	private final Long id;
	private final String userId;
	private final String password;
	private final String name;
	private final String email;
	private final String phone;
	private final String address;

	public ModifyUserTarget(Long id, String userId, String password, String name, String email, String phone,
		String address) {
		this.id = id;
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public User toEntity() {
		return new User(id, userId, password, name, email, phone, address);
	}
}
