package com.flab.foodeats.user.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component
public class UserInfoEncoder {

	public String passwordEncoder(String inputPassword) {
		StringBuffer result = new StringBuffer();

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");

			byte[] salt = "Hello! This is Salt.".getBytes();
			digest.reset();
			digest.update(salt);
			byte[] chars = digest.digest(inputPassword.getBytes("UTF-8"));

			for(int i=0;i<chars.length;i++) {
				String hex = Integer.toHexString(0xff & chars[i]);
				if(hex.length()==1) result.append("0");
				result.append(hex);
			}

		}catch (Exception e) {
			e.printStackTrace();
		}

		return result.toString();
	}

}
