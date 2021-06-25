package com.flab.foodeats.member;

public interface MemberService {
    //회원가입
    void join(Member member);
    Member login(String id, String pass);

    Member findMember(String memberId);



}
