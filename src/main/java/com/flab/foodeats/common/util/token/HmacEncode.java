package com.flab.foodeats.common.util.token;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

@Component
public class HmacEncode {

	public static final String SECRET_KEY = "TokenSecretKey";

	public byte[] hmac(String secret, String data, String algorithms)
		throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {

		//1. SecretKeySpec 클래스를 사용한 키 생성
		SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes("utf-8"), algorithms);

		//2. 지정된  MAC 알고리즘을 구현하는 Mac 객체를 작성
		Mac hasher = Mac.getInstance(algorithms);

		//3. 키를 사용해 이 Mac 객체를 초기화
		hasher.init(secretKey);

		//4. 암호화 하려는 데이터의 바이트의 배열을 처리해 MAC 조작을 종료
		return hasher.doFinal(data.getBytes());
	}

	public String hmacAndBase64(String secret, String data, String algorithms) throws Exception {

		byte[] hmac = hmac(secret, data, algorithms);
		return Base64.getEncoder().withoutPadding().encodeToString(hmac);
	}
}