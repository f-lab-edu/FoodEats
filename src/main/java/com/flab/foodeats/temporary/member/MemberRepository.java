package com.flab.foodeats.temporary.member;

public interface MemberRepository {

    void save(Member member);
    Member find(String id, String pass);

    Member findById(String memberId);


}
