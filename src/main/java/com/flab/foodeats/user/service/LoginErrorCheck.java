package com.flab.foodeats.user.service;

import org.springframework.stereotype.Service;

import com.flab.foodeats.user.model.code.ErrorUserCode;

@Service
public class LoginErrorCheck {

	private UserInfoEncoder userInfoEncoder;

	public LoginErrorCheck(UserInfoEncoder userInfoEncoder) {
		this.userInfoEncoder = userInfoEncoder;
	}

	public void validationLogin(String SessionPassword, String InputPassword) {
		if (!SessionPassword.equals(userInfoEncoder.passwordEncoder(InputPassword))) {
			throw new IllegalArgumentException(ErrorUserCode.PASSWORD_NOT_MATCH.getMessage());
		}
	}

	public void notExistUserValid(String id) {
		if (id == null) {
			throw new NullPointerException(ErrorUserCode.ID_NOT_EXIST.getMessage());
		}
	}

}
