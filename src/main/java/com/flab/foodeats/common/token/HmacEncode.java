package com.flab.foodeats.common.token;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

@Component
public class HmacEncode {

	public static final String SECRET_KEY = "TokenSecretKey";

	public static String HmacAndBase64(String secret, String data, String Algorithms)
		throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {

		//1. SecretKeySpec 클래스를 사용한 키 생성
		SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes("utf-8"), Algorithms);

		//2. 지정된  MAC 알고리즘을 구현하는 Mac 객체를 작성
		Mac hasher = Mac.getInstance(Algorithms);

		//3. 키를 사용해 이 Mac 객체를 초기화
		hasher.init(secretKey);

		//4. 암호화 하려는 데이터의 바이트의 배열을 처리해 MAC 조작을 종료
		byte[] hash = hasher.doFinal(data.getBytes());

		//5. Base 64 Encode to String
		return Base64.encodeBase64String(hash);
	}
}