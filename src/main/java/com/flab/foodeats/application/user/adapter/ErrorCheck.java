package com.flab.foodeats.application.user.adapter;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.flab.foodeats.common.response.ErrorUserCode;
import com.flab.foodeats.domain.user.User;

@Service
public class ErrorCheck {

	public void alreadyExistUserInfo(User userInfo) {
		if (userInfo != null) {
			throw new DuplicateKeyException(ErrorUserCode.ID_EXIST.getMessage());
		}
	}

	public void notExistUserInfo(User userInfo) {
		if (userInfo == null) {
			throw new NullPointerException(ErrorUserCode.ID_NOT_EXIST.getMessage());
		}
	}

	public void validateLoginInfo(String getPasswordInDatabase, String getPasswordInEnteredInfo) {
		if (!getPasswordInDatabase.equals(getPasswordInEnteredInfo)) {
			throw new IllegalArgumentException(ErrorUserCode.PASSWORD_NOT_MATCH.getMessage());
		}
	}
}