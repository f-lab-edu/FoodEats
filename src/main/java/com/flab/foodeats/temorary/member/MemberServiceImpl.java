package com.flab.foodeats.temorary.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 자바 기반 코드 수정 (리뷰 반영)
 * @author eunsoo
 */

@Component
public class MemberServiceImpl implements MemberService{
    private final MemberInfo memberRepository;

    @Autowired
    public MemberServiceImpl(MemberInfo memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public String login(String id, String pass) {
        return "로그인 성공";
    }
}
