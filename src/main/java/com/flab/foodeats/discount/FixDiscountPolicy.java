package com.flab.foodeats.discount;

//import hello.core.member.Grade;
//import hello.core.member.Member;

import com.flab.foodeats.member.Grade;
import com.flab.foodeats.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return discountFixAmount;
        }
        else{
            return 0;
        }

    }
}
