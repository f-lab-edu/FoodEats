package com.flab.foodeats.mapper;

import com.flab.foodeats.model.LoginForm;
import com.flab.foodeats.model.Member;
import org.apache.ibatis.annotations.Mapper;

/**
 * 로그인 기반 코드
 * @author yusok
 */

@Mapper
public interface MemberMapper {
    // 회원정보 저장
    void save(String arg0, String arg1, String arg2);
    // 비밀번호 찾기
    String findPassword(String arg0);
    // 특정회원의 id, password만 받아오기
    LoginForm findIdPass(String arg0);
    // 특정회원의 정보 수정하기
    int updateInfo(String arg0,String arg1, String arg2);

    // 특정회원의 모든 정보 찾아오기 (추후 사용을 위해)
    Member findAll(String arg0);
}