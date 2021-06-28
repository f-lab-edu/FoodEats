package com.flab.foodeats.temporary.discount;

//import hello.core.member.Member;


import com.flab.foodeats.temporary.member.Member;

public interface DiscountPolicy {


    int discount(Member member, int price);

}
