package com.flab.foodeats.temorary.discount;

import com.flab.foodeats.temorary.member.Member;
import com.flab.foodeats.temorary.member.grade.Grade;
import org.springframework.stereotype.Component;

/**
 * 자바 기반 코드 수정 (리뷰 반영)
 * @author eunsoo
 */

// 고정할인정책
@Component
public class FixDiscountPolicy implements DiscountPolicy {

    // 1000원 고정할인
    private int discountFixAmount = 1000;

    // VIP등급만 할인
    @Override
    public int discount(Member member, int price) {
        System.out.println("머지");
        if(member.getGrade() == Grade.VIP){
            return discountFixAmount;
        }
        else{
            return 0;
        }

    }
}
