package com.flab.foodeats.common.util.token;

import static com.flab.foodeats.common.util.token.HmacEncode.*;

import java.util.Base64;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import com.flab.foodeats.common.auth.AuthConstants;
import com.flab.foodeats.common.auth.AuthInfo;
import com.flab.foodeats.domain.user.UserType;

@Service
public class TokenServiceImpl implements TokenService {

	private final HmacEncode hmacEncode;

	public TokenServiceImpl(HmacEncode hmacEncode) {
		this.hmacEncode = hmacEncode;
	}

	@Override
	public boolean validateHeader(String header) throws Exception {
		if (!header.startsWith(AuthConstants.TOKEN_TYPE)) {
			throw new Exception("Wrong Header");
		}
		return true;
	}

	@Override
	public String validateToken(String header) throws Exception {
		String token = header.substring(7);
		String[] tokenSplit = token.split("\\.");

		String tokenHeader = tokenSplit[0];    // 1. 헤더 인코딩 값
		String tokenPayload = tokenSplit[1];   // 2. 페이로드 인코딩 값
		String tokenSignature = tokenSplit[2]; // 3. (HEADER인코딩값.PAYLOAD인코딩값) -> 해시 -> 인코딩 된 값

		String headerPayloadHash = new String(hmacEncode.hmac(SECRET_KEY, tokenHeader+"."+tokenPayload, "HmacSHA256"));
		String signatureDecode = new String(Base64.getDecoder().decode(tokenSignature));

		if (headerPayloadHash.equals(signatureDecode)) {
			return token;
		}
		throw new Exception("Not validate Token");
	}

	@Override
	public boolean validateTokenExpiration(String token) throws Exception {
		String tokenPayloadDecode = getTokenPayloadDecode(token);

		JSONParser parser = new JSONParser();
		JSONObject parse = (JSONObject)parser.parse(tokenPayloadDecode);

		Long expiration = Long.valueOf((String)parse.get("exp"));
		if (System.currentTimeMillis() > expiration) {
			throw new Exception("Not validate Token");
		}
		return true;
	}

	@Override
	public AuthInfo getAuthInfo(String token) throws Exception {
		String tokenPayloadDecode = getTokenPayloadDecode(token);

		JSONParser parser = new JSONParser();
		JSONObject parse = (JSONObject)parser.parse(tokenPayloadDecode);

		long id = Long.valueOf((String)parse.get("id"));
		String userId = (String)parse.get("userId");
		UserType userType = UserType.valueOf((String)parse.get("userType"));

		return new AuthInfo(id, userId, userType);
	}

	private String getTokenPayloadDecode(String token) {
		String[] tokenSplit = token.split("\\.");
		String tokenPayload = tokenSplit[1];

		return new String(Base64.getDecoder().decode(tokenPayload));
	}
}
