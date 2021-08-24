package com.flab.foodeats.domain.user.auth;

import com.flab.foodeats.domain.user.UserType;

public class AuthInfo {
	public static String AUTH_KEY = "AUTH_KEY"; // session에 key로 스트링을 넣을 때 사용

	private String userId;
	private UserType userType;

	protected AuthInfo(){

	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public static AuthInfo of(String userId, UserType userType){
		AuthInfo authInfo =  new AuthInfo();
		authInfo.userId = userId;
		authInfo.userType = userType;
		return authInfo;
	}

	public static AuthInfo consuemrOf(String userId){
		return of(userId,UserType.CONSUMER);
	}
	public static AuthInfo merchantOf(String userId){
		return of(userId,UserType.MERCHANT);
	}
	public static AuthInfo riderOf(String userId){
		return of(userId,UserType.RIDER);
	}

	public AuthInfo(String userId, UserType userType) {
		this.userId = userId;
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "AuthInfo{" +
			"userId='" + userId + '\'' +
			", userType=" + userType +
			'}';
	}
}

