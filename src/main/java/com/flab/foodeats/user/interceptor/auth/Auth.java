package com.flab.foodeats.user.interceptor.auth;

public class Auth {
    public static String CUNSUMER_KEY = "CunsumerLogin"; // session에 key로 스트링을 넣을 때 사용

    private String id;

    public Auth() {
    }

    public Auth(String id) {
        this.id = id;
    }
}
