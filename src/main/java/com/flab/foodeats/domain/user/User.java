package com.flab.foodeats.domain.user;

import com.flab.foodeats.common.Encryption;

public class User {

	private String userId;
	private String password;
	private String name;
	private String email;
	private String phone;
	private String address;

	public User() {
	}

	public User(String userId, String password, String name, String email, String phone, String address) {
		this.userId = userId;
		this.password = Encryption.encoder(password);
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
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
}
