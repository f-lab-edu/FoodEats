package com.flab.foodeats.temorary.member.grade;

import com.flab.foodeats.temorary.member.Member;

public interface SetGrade {
    //회원등급조정  //총 주문가격을 기준으로
    void gradeSet (Member member, int price);

}
