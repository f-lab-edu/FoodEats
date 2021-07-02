package com.flab.foodeats.temorary.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberInfo implements MemberInfo {
    private static Map<String, Member> store = new HashMap<>();

    // id 저장
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);  // HashMap에 <키,값>
    }

    @Override
    public Member find(String id) {
        //System.out.println("로그인 성공  : " + store.get(id));
        // 그렇게
        return store.get(id);
    }
}
