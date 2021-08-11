package com.flab.foodeats.user.service;

import java.security.MessageDigest;

public class UserInfoEncoder {


	private String password;


	public UserInfoEncoder() {

	}

	public UserInfoEncoder(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String encodePassword(String wantToEncodePasswordEncode) {
		this.password = encoder(wantToEncodePasswordEncode);
		return this.password;
	}

	public String encoder(String beforeEncodePassword) {
		StringBuffer result = new StringBuffer();

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");

			byte[] salt = "Hello! This is Salt.".getBytes();
			digest.reset();
			digest.update(salt);
			byte[] chars = digest.digest(beforeEncodePassword.getBytes("UTF-8"));

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
