package com.flab.foodeats.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.flab.foodeats.user.model.code.ErrorUserCode;

@Service
public class LoginErrorCheck {

	private PasswordEncoder passwordEncoder;

	public LoginErrorCheck(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public void validationLogin(String InputPassword, String SessionPassword){
		if(!passwordEncoder.matches(SessionPassword, InputPassword)){
			throw new IllegalArgumentException(ErrorUserCode.PASSWORD_NOT_MATCH.getMessage());
		}
	}

	public void notExistUserValid(String id){
		if(id == null){
			throw new NullPointerException(ErrorUserCode.ID_NOT_EXIST.getMessage());
		}
	}


}
