package com.flab.foodeats.discount;

//import hello.core.member.Grade;
//import hello.core.member.Member;


import com.flab.foodeats.member.Grade;
import com.flab.foodeats.member.Member;
import org.springframework.stereotype.Component;

@Component
public class RateDiscountPolicy implements DiscountPolicy{

    private double BronzPercent = 0.05;
    private double SilverPercent = 0.1;
    private double VIPPercent = 0.2;


    @Override
    public int discount(Member member, int price) {
        if(member.getGrade()== Grade.BRONZE){

            double last_price =  price * (1.0-BronzPercent);

            return  (int)last_price;
        }
        else if(member.getGrade()== Grade.SILVER){

            double last_price =  price * (1.0-SilverPercent);

            return  (int)last_price;
        }
        else if(member.getGrade()== Grade.VIP){

            double last_price =  price * (1.0-VIPPercent);

            return  (int)last_price;
        }
        else{
            return price;
        }

    }
}
