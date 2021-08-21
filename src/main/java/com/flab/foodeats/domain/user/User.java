package com.flab.foodeats.domain.user;

public class User {

	/**
	 * todo
	 * Entity
	 * https://geminikim.medium.com/%EA%B0%9C%EC%9D%B8%EC%B7%A8%ED%96%A5-jpa-%EC%82%AC%EC%9A%A9%EA%B8%B0-2%ED%8E%B8-entity-with-getter-setter-and-test-a0305af69090
	 * get set 사용해도 된다
	 * 하지만 set은 외부에서 기본키를 수정할 수 있다는 단점이 발생한다.
	 * > 따라서 외부에서 수정하지 못하게 set을 사용하게 해 주어야 한다.
	 */

	private String userId;
	private String password;
	private String name;

	public User(String userId, String password, String name) {
		this.userId = userId;
		this.password = password;
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}
}
