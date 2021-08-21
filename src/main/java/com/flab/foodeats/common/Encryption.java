package com.flab.foodeats.common;

import java.security.MessageDigest;

public class Encryption {

	public static String encoder(String password) {
		StringBuffer result = new StringBuffer();

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");

			byte[] salt = "Hello! This is Salt.".getBytes();
			digest.reset();
			digest.update(salt);
			byte[] chars = digest.digest(password.getBytes("UTF-8"));

			for (int i = 0; i < chars.length; i++) {
				String hex = Integer.toHexString(0xff & chars[i]);
				if (hex.length() == 1)
					result.append("0");
				result.append(hex);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result.toString();
	}

}