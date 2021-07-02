package com.flab.foodeats.temorary.member.grade;

import com.flab.foodeats.temorary.member.Member;

/**
 * 자바 기반 코드 수정 (리뷰 반영)
 * @author eunsoo
 */

public interface SetGrade {
    //회원등급조정  //총 주문가격을 기준으로
    void gradeSet (Member member, int price);

}
