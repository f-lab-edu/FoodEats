package com.flab.foodeats.temorary.member.grade;

import com.flab.foodeats.temorary.member.Member;
import org.springframework.stereotype.Component;

@Component
public class SetGradeImpl implements SetGrade {
    //회원 등급 조회 //총 주문가격을 기준으로
    @Override
    public void gradeSet(Member member, int price) {
        if(price < 20000){
            member.setGrade(Grade.BASIC);
        }
        else if(price<40000){
            member.setGrade(Grade.BRONZE);
        }
        else if(price<60000){
            member.setGrade(Grade.SILVER);
        }
        else if(price<80000){
            member.setGrade(Grade.VIP);
        }
    }
}
