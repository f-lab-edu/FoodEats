package com.flab.foodeats.temporary.member;

public interface MemberService {
    //회원가입
    void join(Member member);
    Member login(String id, String pass);

    Member findMember(String memberId);



}
