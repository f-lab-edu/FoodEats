package com.flab.foodeats.common.token;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import net.minidev.json.JSONObject;

import com.flab.foodeats.common.auth.AuthInfo;

@Component
public class TokenUtils {

	private final HmacEncode hmacEncode;

	public TokenUtils(HmacEncode hmacEncode) {
		this.hmacEncode = hmacEncode;
	}

	public String createToken(AuthInfo authInfo)
		throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {

		String encodedheader = createHeader();
		String encodedpayload = createPayload(authInfo);
		String signature = encodedheader + "." + encodedpayload;

		String hashedSignature = hmacEncode.HmacAndBase64(HmacEncode.SECRET_KEY, signature, "HmacSHA256");
		String encodedsignature = Base64.getEncoder().withoutPadding().encodeToString(hashedSignature.getBytes());
		return encodedheader + "." + encodedpayload + "." + encodedsignature;
	}

	private String createHeader() {
		Map<String, String> header = new HashMap<>(2);
		header.put("typ", "JWT");
		header.put("alg", "HS256");

		JSONObject jsonObject = new JSONObject(header);
		String headerToString = String.valueOf(jsonObject);
		String encodedHeader = Base64.getEncoder()
			.withoutPadding()
			.encodeToString(headerToString.getBytes());
		return encodedHeader;
	}

	private String createPayload(AuthInfo authInfo) {
		Map<String, String> payload = new HashMap<>(7);

		payload.put("iss", "flab.com"); // 토큰 발급자
		payload.put("sub", "AuthorizationToken"); // 토큰 제목
		payload.put("iat", String.valueOf(System.currentTimeMillis())); // 토큰이 발급된 시간
		payload.put("exp", String.valueOf(System.currentTimeMillis() + 30 * 60000)); // 토큰 만료시간 30분
		payload.put("id", String.valueOf(authInfo.getId()));
		payload.put("userId", authInfo.getUserId());
		payload.put("userType", String.valueOf(authInfo.getUserType()));

		JSONObject jsonObject = new JSONObject(payload);
		String payloadToString = String.valueOf(jsonObject);
		String encodedPayload = Base64.getEncoder()
			.withoutPadding()
			.encodeToString(payloadToString.getBytes());
		return encodedPayload;
	}
}
