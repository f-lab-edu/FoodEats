package com.flab.foodeats.temorary.member;

public interface MemberService {
    //회원가입
    void join(Member member);
    //로그인
    String login(String id, String pass);
}
