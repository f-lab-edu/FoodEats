package com.flab.foodeats.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository {

    private static Map<String, Member> store = new HashMap<>();

    // id 저장
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);  // HashMap에 <키,값>
    }

    @Override
    public Member find(String id, String pass) {
        //System.out.println("로그인 성공  : " + store.get(id));

        // 그렇게
        return store.get(id);

    }

    @Override
    public Member findById(String memberId) {
        return store.get(memberId);
    }

    // id 찾기



}
