package com.flab.foodeats.temorary.member;

/**
 * 자바 기반 코드 수정 (리뷰 반영)
 * @author eunsoo
 */

public interface MemberService {
    //회원가입
    void join(Member member);
    //로그인
    String login(String id, String pass);
}
