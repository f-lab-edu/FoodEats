package com.flab.foodeats.temporary.order;


import com.flab.foodeats.temporary.member.Grade;

public class OrderGradeImpl implements OrderGrade{

    @Override
    public Order gradeSet(Order order, int price) {


        if(price < 20000){
            order.getMember().setGrade(Grade.BASIC);
        }
        else if(price<40000){
            order.getMember().setGrade(Grade.BRONZE);
        }
        else if(price<60000){
            order.getMember().setGrade(Grade.SILVER);
        }
        else if(price<80000){
            order.getMember().setGrade(Grade.VIP);
        }

        return order;
    }
}
