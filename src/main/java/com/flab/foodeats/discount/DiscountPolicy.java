package com.flab.foodeats.discount;

//import hello.core.member.Member;


import com.flab.foodeats.member.Member;

public interface DiscountPolicy {


    int discount(Member member, int price);

}
