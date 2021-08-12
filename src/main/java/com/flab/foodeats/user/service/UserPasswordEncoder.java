package com.flab.foodeats.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.flab.foodeats.user.model.InsertFormDTO;
import com.flab.foodeats.user.model.UpdateFormDTO;

//@Service
public class UserPasswordEncoder {

	private PasswordEncoder passwordEncoder;

	public UserPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public void loginEncoder(InsertFormDTO insertFormDTO) {
		String encodePassword = passwordEncoder.encode(insertFormDTO.getPassword());
		insertFormDTO.setPassword(encodePassword);
	}

	public void updateEncoder(UpdateFormDTO updateFormDTO){
		String encodePassword = passwordEncoder.encode(updateFormDTO.getPassword());
		updateFormDTO.setPassword(encodePassword);
	}

}
