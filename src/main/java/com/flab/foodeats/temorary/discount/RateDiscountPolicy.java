package com.flab.foodeats.temorary.discount;

import com.flab.foodeats.temorary.member.Member;
import com.flab.foodeats.temorary.member.grade.Grade;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

// 등급할인 정책
@Component
@Qualifier("mainDiscountPolicy")
public class RateDiscountPolicy implements DiscountPolicy{

    // 브론즈5% 실버10% VIP20% 할인
    private double BronzPercent = 0.05;
    private double SilverPercent = 0.1;
    private double VIPPercent = 0.2;

    @Override
    public int discount(Member member, int price) {

        // 브론즈 5퍼할인
        if(member.getGrade()== Grade.BRONZE){
            double last_price =  price * (1.0-BronzPercent);
            return  (int)last_price;
        }// 실버 10퍼할인
        else if(member.getGrade()== Grade.SILVER){
            double last_price =  price * (1.0-SilverPercent);
            return  (int)last_price;
        }// VIP 20퍼할인
        else if(member.getGrade()== Grade.VIP){
            double last_price =  price * (1.0-VIPPercent);
            return  (int)last_price;
        }// BASIC 할인X
        else{
            return price;
        }

    }



}
