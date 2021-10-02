package com.flab.foodeats.common.util.token;

import com.flab.foodeats.common.auth.AuthInfo;

public interface TokenService {

	boolean validateHeader(String header) throws Exception;

	String validateToken(String header) throws Exception;

	boolean validateTokenExpiration(String token) throws Exception;

	AuthInfo getAuthInfo(String token) throws Exception;
}
