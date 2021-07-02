package com.flab.foodeats.temorary.member;

public interface MemberInfo {
    //DB저장
    void save(Member member);
    //DB조회
    Member find(String id);
}
