package com.flab.foodeats.temorary.discount;

import com.flab.foodeats.temorary.member.Member;
import com.flab.foodeats.temorary.member.grade.Grade;
import org.springframework.stereotype.Component;

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
