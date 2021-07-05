package com.flab.foodeats.temorary.member;

/**
 * 자바 기반 코드 수정 (리뷰 반영)
 * @author eunsoo
 */

public interface MemberInfo {
    //DB저장
    void save(Member member);
    //DB조회
    Member find(String id);
}
