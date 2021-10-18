package com.flab.foodeats.application.user;


import com.flab.foodeats.domain.user.Consumer;
import com.flab.foodeats.domain.user.Merchant;
import com.flab.foodeats.domain.user.Rider;

import com.flab.foodeats.domain.user.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginUserResponse {

	private long id;
	private String userId;
	private String password;
	private String name;
	private String email;
	private String phone;
	private String address;
	private String token;

	@Builder
	public LoginUserResponse(long id, String userId, String password, String name, String email, String phone,
		String address, String token) {
		this.id = id;
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.token = token;
	}


	public static LoginUserResponse ofConsumer(Consumer user, String token) {
		return LoginUserResponse.builder()
			.id(user.getId())
			.userId(user.getUserId())
			.password(user.getPassword())
			.name(user.getName())
			.email(user.getEmail())
			.phone(user.getPhone())
			.address(user.getAddress())
			.token(token)
			.build();
	}

	public static LoginUserResponse ofMerchant(Merchant user, String token) {
		return LoginUserResponse.builder()
			.id(user.getId())
			.userId(user.getUserId())
			.password(user.getPassword())
			.name(user.getName())
			.email(user.getEmail())
			.phone(user.getPhone())
			.address(user.getAddress())
			.token(token)
			.build();
	}

	public static LoginUserResponse ofRider(Rider user, String token) {
		return LoginUserResponse.builder()
			.id(user.getId())
			.userId(user.getUserId())
			.password(user.getPassword())
			.name(user.getName())
			.email(user.getEmail())
			.phone(user.getPhone())
			.address(user.getAddress())
			.token(token)
			.build();
	}

}
