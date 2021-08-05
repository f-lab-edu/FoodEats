package com.flab.foodeats.user.service;

import org.springframework.stereotype.Service;

import com.flab.foodeats.user.model.code.ErrorUserCode;

@Service
public class LoginErrorCheck {

	public void UserNotExist(String id){
		if(id == null){
			throw new NullPointerException(ErrorUserCode.ID_NOT_EXIST.getMessage());
		}
	}

	public void PasswordUnMatch(String InputPassword, String SessionPassword){
		if(!InputPassword.equals(SessionPassword)){
			throw new IllegalArgumentException(ErrorUserCode.PASSWORD_NOT_MATCH.getMessage());
		}
	}

}
