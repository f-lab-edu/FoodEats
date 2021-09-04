package com.flab.foodeats.common.auth;

import java.io.Serializable;

import com.flab.foodeats.domain.user.UserType;

public class AuthInfo implements Serializable {
	public static String AUTH_KEY = "AUTH_KEY"; // session에 key로 스트링을 넣을 때 사용

	private Long id;
	private String userId;
	private UserType userType;


	protected AuthInfo(){

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public static AuthInfo of(Long id, String userId, UserType userType){
		AuthInfo authInfo =  new AuthInfo();
		authInfo.id = id;
		authInfo.userId = userId;
		authInfo.userType = userType;
		return authInfo;
	}

	public static AuthInfo consuemrOf(Long id ,String userId){
		return of(id, userId,UserType.CONSUMER);
	}
	public static AuthInfo merchantOf(Long id ,String userId){
		return of(id, userId,UserType.MERCHANT);
	}
	public static AuthInfo riderOf(Long id ,String userId){
		return of(id, userId,UserType.RIDER);
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

