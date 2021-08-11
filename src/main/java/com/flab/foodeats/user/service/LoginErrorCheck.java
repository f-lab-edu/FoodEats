package com.flab.foodeats.user.service;

import org.springframework.stereotype.Service;

import com.flab.foodeats.user.model.code.ErrorUserCode;

@Service
public class LoginErrorCheck {

	public void validationLogin(String getPasswordInSession, String getPasswordInLoginForm) {

		if (!getPasswordInSession.equals(new UserInfoEncoder().encodePassword(getPasswordInLoginForm))) {
			throw new IllegalArgumentException(ErrorUserCode.PASSWORD_NOT_MATCH.getMessage());
		}
	}

	public void notExistUserValid(String id) {
		if (id == null) {
			throw new NullPointerException(ErrorUserCode.ID_NOT_EXIST.getMessage());
		}
	}

}
