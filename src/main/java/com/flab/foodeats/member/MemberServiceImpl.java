package com.flab.foodeats.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{
    //인터페이스 구현체가 하나면 그냥 뒤에 impl 쓰는게 관례

   // private final MemberRepository memberRepository = new MemoryMemberRepository();

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member login(String id, String pass) {

        return memberRepository.find(id,pass);
    }








    @Override
    public Member findMember(String memberId) {
        return memberRepository.findById(memberId);
    }
}
