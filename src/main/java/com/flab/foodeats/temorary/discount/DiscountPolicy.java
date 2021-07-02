package com.flab.foodeats.temorary.discount;

import com.flab.foodeats.temorary.member.Member;
public interface DiscountPolicy {

    // 할인 인터페이스 // 회원정보와 총 주문가격 (매개변수)
    int discount(Member member, int price);

}
