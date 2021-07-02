package com.flab.foodeats.temorary.discount;

import com.flab.foodeats.temorary.member.Member;

/**
 * 자바 기반 코드 수정 (리뷰 반영)
 * @author eunsoo
 */

public interface DiscountPolicy {

    // 할인 인터페이스 // 회원정보와 총 주문가격 (매개변수)
    int discount(Member member, int price);

}
